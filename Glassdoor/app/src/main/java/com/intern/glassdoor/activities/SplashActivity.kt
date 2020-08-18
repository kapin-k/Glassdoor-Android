package com.intern.glassdoor.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.intern.glassdoor.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        appLogoIV.setImageResource(R.drawable.glassdoor_logo)
        removeActionBar()
        delayMainActivityTransition()
    }

    private fun removeActionBar() {
        supportActionBar?.let { actionBar ->
            actionBar.hide()
        }
    }

    private fun delayMainActivityTransition() {
        Handler().postDelayed(Runnable {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }, 2000)
    }
}
