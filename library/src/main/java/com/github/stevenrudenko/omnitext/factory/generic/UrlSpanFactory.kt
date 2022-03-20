package com.github.stevenrudenko.omnitext.factory.generic

import android.util.Patterns
import com.github.stevenrudenko.omnitext.factory.BaseSpanFactory
import com.github.stevenrudenko.omnitext.OmniSpan
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_URL
import java.util.regex.Pattern

/** User to search URL spans in a text. */
class UrlSpanFactory(
    val icon: Int = 0
): BaseSpanFactory() {
    override val searchPattern: Pattern
        get() = Patterns.WEB_URL
    override val validatePattern: Pattern = searchPattern

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val part = text.subSequence(start, end).toString()
        return OmniSpan(
            TYPE_URL,
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