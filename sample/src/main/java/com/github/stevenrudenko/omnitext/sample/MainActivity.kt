package com.github.stevenrudenko.omnitext.sample

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.stevenrudenko.omnitext.OmniTextView
import com.github.stevenrudenko.omnitext.utils.OmniIntentUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<OmniTextView>(R.id.omniSample)
        textView.onOmniAction = { omni ->
            Toast.makeText(applicationContext, omni.text, Toast.LENGTH_SHORT).show()
            OmniIntentUtils.createIntent(omni)?.let { intent ->
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }

        findViewById<CompoundButton>(R.id.omniToggle).setOnCheckedChangeListener { _, enabled ->
            textView.omniEnabled = enabled
        }
    }
}