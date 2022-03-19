package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_DINERS_CLUB
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search Diners spans in a text. */
open class DinersCardSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_DINERS_CLUB
    override val validatePattern: Pattern
        get() = PATTERN
    override val validIconId: Int
        get() = R.drawable.omni_card_diners_valid
    override val invalidIconId: Int
        get() = R.drawable.omni_card_diners_invalid

    companion object {
        private var PATTERN = Pattern.compile("3(?:0[0-5]|[68][0-9])[0-9]{11}")
    }

}