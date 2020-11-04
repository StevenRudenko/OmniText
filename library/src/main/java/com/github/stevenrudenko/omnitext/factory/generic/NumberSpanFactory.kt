package com.github.stevenrudenko.omnitext.factory.generic

import com.github.stevenrudenko.omnitext.factory.BaseSpanFactory
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_NUMBER
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search number spans in a text. */
class NumberSpanFactory(
    val icon: Int = 0
): BaseSpanFactory() {
    override val searchPattern: Pattern
        get() = Pattern.compile("(\\d*\\.)?\\d+")

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val part = text.subSequence(start, end).toString()
        return OmniSpan(
            TYPE_NUMBER,
            start,
            end,
            part,
            validate(text),
            icon
        )
    }

    override fun validate(text: CharSequence): Boolean = true

    override fun prettify(text: CharSequence): CharSequence = text

    override fun minify(text: CharSequence): CharSequence = text

}