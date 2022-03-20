package com.github.stevenrudenko.omnitext.utils

import android.content.Intent
import android.net.Uri
import com.github.stevenrudenko.omnitext.OmniSpan
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_EMAIL
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_PHONE
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_URL

object OmniIntentUtils {

    fun createIntent(omni: OmniSpan): Intent? {
        return when (omni.type) {
            TYPE_URL -> {
                val url = if (omni.text.startsWith("http")) {
                    omni.text.toString()
                } else {
                    "https://${omni.text}"
                }
                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addCategory(Intent.CATEGORY_BROWSABLE)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
            }
            TYPE_EMAIL -> Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${omni.text}")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            TYPE_PHONE -> Intent(Intent.ACTION_DIAL, Uri.parse("tel:${omni.text}"))
            else -> null
        }
    }

}