package com.github.stevenrudenko.omnitext.sample

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.stevenrudenko.omnitext.OmniTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<OmniTextView>(R.id.omniSample)
        textView.onOmniAction = {
            Toast.makeText(applicationContext, it.text, Toast.LENGTH_SHORT).show()
        }

        findViewById<CompoundButton>(R.id.omniToggle).setOnCheckedChangeListener { _, enabled ->
            textView.omniEnabled = enabled
        }
    }
}