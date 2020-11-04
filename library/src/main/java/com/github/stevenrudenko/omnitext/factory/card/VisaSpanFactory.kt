package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_VISA
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search Visa card spans in a text. */
open class VisaSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_VISA
    override val searchPattern: Pattern
        get() = PATTERN

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val result = super.buildOf(text, start, end)
        return result.copy(
            icon = if (result.valid) R.drawable.omni_card_visa_valid else R.drawable.omni_card_visa_invalid
        )
    }

    companion object {
        private var PATTERN = Pattern.compile("(?:4[0-9]{12}(?:[0-9]{3}))")
    }

}