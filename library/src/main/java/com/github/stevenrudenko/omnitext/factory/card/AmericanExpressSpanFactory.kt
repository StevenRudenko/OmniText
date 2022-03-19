package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_AM_EXPRESS
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search American Express spans in a text. */
open class AmericanExpressSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_AM_EXPRESS
    override val validatePattern: Pattern
        get() = PATTERN
    override val validIconId: Int
        get() = R.drawable.omni_card_ame_valid
    override val invalidIconId: Int
        get() = R.drawable.omni_card_ame_invalid

    companion object {
        private var PATTERN = Pattern.compile("3[47][0-9]{13}")
    }

}