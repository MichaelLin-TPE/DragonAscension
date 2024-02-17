package com.dragon.ascension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun <T>goOtherPage(activityPage: Class<T>){
        val intent = Intent(this,activityPage)
        startActivity(intent)
        overridePendingTransition(R.anim.activity_fade_in,R.anim.activity_fake_out)
    }

}