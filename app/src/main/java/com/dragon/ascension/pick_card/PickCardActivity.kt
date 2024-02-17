package com.dragon.ascension.pick_card

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dragon.ascension.BaseActivity
import com.dragon.ascension.CardData
import com.dragon.ascension.R
import com.dragon.ascension.databinding.ActivityPickCardBinding
import com.dragon.ascension.tool.Tool.dpToPx
import com.dragon.ascension.tool.Tool.getScreenWidth

class PickCardActivity : BaseActivity() {

    private lateinit var viewModel: PickCardViewModel
    private lateinit var dataBinding: ActivityPickCardBinding
    private var cardList = mutableListOf<CardData>()
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_pick_card)
        viewModel = ViewModelProvider(this)[PickCardViewModel::class.java]

        viewModel.onCreate()
        initHandleLiveData()
    }

    private fun initHandleLiveData() {
        viewModel.addCardLiveData.observe(this) { cardList ->
            PickCardActivity@this.cardList = cardList
            startToAddView()

        }
    }

    private val lastTouchX = HashMap<View, Float>()
    private val lastTouchY = HashMap<View, Float>()

    private val originalY = HashMap<View,Float>()


    @SuppressLint("ClickableViewAccessibility")
    private fun startToAddView() {
        if (index < cardList.size){
            val view = View.inflate(this, R.layout.item_card_layout, null)
            dataBinding.root.addView(view)
            val root = view.findViewById<ConstraintLayout>(R.id.root)
            val ivCard = view.findViewById<ImageView>(R.id.card)
            view.visibility = View.INVISIBLE

            root.post {
                val screenWidth = getScreenWidth()
                val cardWidth = dpToPx(200)
                val targetX: Float = ((screenWidth - cardWidth) / 2).toFloat()
                val layoutParams = root.layoutParams
                layoutParams.width = dpToPx(200)
                layoutParams.height = dpToPx(280)
                root.layoutParams = layoutParams

                root.x = targetX
                root.y = 120f + 50f * index
                originalY[root] = 120f + 50f * index
                view.visibility = View.VISIBLE

                root.setOnTouchListener { v, event ->
                    val x = event.rawX
                    val y = event.rawY

                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            // 當手指按下時，記錄該視圖的起始點
                            lastTouchX[view] = x
                            lastTouchY[view] = y
                            true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            // 計算移動的距離
                            val dx = x - (lastTouchX[view] ?: x)
                            val dy = y - (lastTouchY[view] ?: y)

                            // 更新 ImageView 的位置
//                            view.translationX += dx
                            view.translationY += dy

                            // 更新該視圖的起始點
                            lastTouchX[view] = x
                            lastTouchY[view] = y
                            true
                        }
                        MotionEvent.ACTION_UP -> {
                            // 處理手指抬起的事件
                            originalY[view]?.let {
                                v.animate()
                                    .y(it)
                                    .setDuration(200)
                                    .start()
                            }

                            true
                        }
                        else -> false
                    }
                }

                index ++
                startToAddView()
            }
        }else{
            index = 0
        }

    }
}
