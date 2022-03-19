package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_DISCOVERY
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search Mastercard spans in a text. */
open class DiscoverSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_DISCOVERY
    override val validatePattern: Pattern
        get() = PATTERN
    override val validIconId: Int
        get() = R.drawable.omni_card_discover_valid
    override val invalidIconId: Int
        get() = R.drawable.omni_card_discover_invalid

    companion object {
        private var PATTERN = Pattern.compile("6(?:011|5[0-9]{2})[0-9]{12}")
    }

}