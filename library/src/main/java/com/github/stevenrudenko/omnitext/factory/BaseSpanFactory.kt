package com.github.stevenrudenko.omnitext.factory

import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search text spans by pattern in a text. */
abstract class BaseSpanFactory : SpanFactory {
    protected abstract val searchPattern: Pattern

    override fun search(text: CharSequence): Array<OmniSpan> {
        val matcher = searchPattern.matcher(text)
        val result = mutableListOf<OmniSpan>()
        var searchStart = 0
        while (searchStart < text.length && matcher.find(searchStart)) {
            val start = matcher.start(0)
            val end = matcher.end(0)
            val span = buildOf(text, start, end)
            result.add(span)

            searchStart = end + 1
        }
        return result.toTypedArray()
    }
}