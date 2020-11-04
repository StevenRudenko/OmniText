package com.github.stevenrudenko.omnitext.factory.generic

import com.github.stevenrudenko.omnitext.factory.BaseSpanFactory
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_TEXT
import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search custom tags spans in a text. */
class TextSpanFactory(
    val tag: String = "omni",
    val icon: Int = 0
): BaseSpanFactory() {
    override val searchPattern: Pattern
        get() = Pattern.compile("\\{$tag\\}(.+?)\\{/$tag\\}", Pattern.DOTALL)

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val part = text.subSequence(start, end)
            .toString()
            .replace("{$tag}", "")
            .replace("{/$tag}", "")
        return OmniSpan(
            TYPE_TEXT,
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