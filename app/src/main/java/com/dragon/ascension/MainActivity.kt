package com.dragon.ascension

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dragon.ascension.databinding.ActivityMainBinding
import com.dragon.ascension.pick_card.PickCardActivity
import com.dragon.ascension.pick_card.PickCardViewModel

class MainActivity : BaseActivity() {

    private lateinit var dataBinding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        val fadeInOut = AnimationUtils.loadAnimation(this,R.anim.fade_in_fade_out)
        dataBinding.tvClickAnyWhere.startAnimation(fadeInOut)

        dataBinding.tvClickAnyWhere.setOnClickListener {
            goOtherPage(PickCardActivity::class.java)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding.tvClickAnyWhere.clearAnimation()
    }



}