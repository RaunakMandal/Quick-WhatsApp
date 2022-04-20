package com.iamriju2000.quickwhatsapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iamriju2000.quickwhatsapp.R
import android.content.pm.ActivityInfo
import android.util.Log
import android.widget.ImageView
import android.widget.Toast

class AboutActivity : AppCompatActivity() {
    private lateinit var logo: ImageView
    private final val PRESS_THRES = 2
    private var current = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        logo = findViewById(R.id.logo)
        logo.setOnClickListener({ view ->
            if (current < PRESS_THRES - 1) {
                current++
            } else {
                current = 0
                sayMyName()
            }
            Log.d("QWP", current.toString())
        })
    }

    private fun sayMyName() {

        Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
    }
}