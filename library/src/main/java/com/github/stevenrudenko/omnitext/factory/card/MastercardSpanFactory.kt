package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_MASTERCARD
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search Mastercard spans in a text. */
open class MastercardSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_MASTERCARD
    override val searchPattern: Pattern
        get() = PATTERN

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val result = super.buildOf(text, start, end)
        return result.copy(
            icon = if (result.valid) R.drawable.omni_card_mc_valid else R.drawable.omni_card_mc_invalid
        )
    }

    companion object {
        private var PATTERN = Pattern.compile(
            "(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}"
        )
    }

}