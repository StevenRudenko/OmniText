package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_DISCOVERY
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search Mastercard spans in a text. */
open class DiscoverSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_DISCOVERY
    override val searchPattern: Pattern
        get() = PATTERN

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val result = super.buildOf(text, start, end)
        return result.copy(
            icon = if (result.valid) R.drawable.omni_card_discover_valid else R.drawable.omni_card_discover_invalid
        )
    }

    companion object {
        private var PATTERN = Pattern.compile("6(?:011|5[0-9]{2})[0-9]{12}")
    }

}