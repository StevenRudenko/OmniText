package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_JCB
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search JCB spans in a text. */
open class JcbSpanFactory : CardSpanFactory() {
    override val type: Int
        get() = TYPE_JCB
    override val validatePattern: Pattern
        get() = PATTERN
    override val validIconId: Int
        get() = R.drawable.omni_card_jcb_valid
    override val invalidIconId: Int
        get() = R.drawable.omni_card_jcb_invalid

    companion object {
        private var PATTERN = Pattern.compile("(?:2131|1800|35\\d{3})\\d{11}")
    }

}