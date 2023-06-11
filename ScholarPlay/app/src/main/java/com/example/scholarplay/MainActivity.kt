package com.example.scholarplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_ScholarPlay)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}