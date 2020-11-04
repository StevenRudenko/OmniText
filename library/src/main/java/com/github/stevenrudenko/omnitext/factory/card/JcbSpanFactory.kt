package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_JCB
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search Mastercard spans in a text. */
open class JcbSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_JCB
    override val searchPattern: Pattern
        get() = PATTERN

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val result = super.buildOf(text, start, end)
        return result.copy(
            icon = if (result.valid) R.drawable.omni_card_jcb_valid else R.drawable.omni_card_jcb_invalid
        )
    }

    companion object {
        private var PATTERN = Pattern.compile("(?:2131|1800|35\\d{3})\\d{11}")
    }

}