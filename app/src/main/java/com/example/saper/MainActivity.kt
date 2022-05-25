package com.example.saper

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user presses the Start Game button */
    fun startGame(@Suppress("UNUSED_PARAMETER")view: View) {
        val intent = Intent(this, SaperSquaresActivity::class.java).apply{}
        startActivity(intent)
    }
}