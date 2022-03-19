package com.github.stevenrudenko.omnitext.factory

import com.github.stevenrudenko.omnitext.OmniSpan
import java.util.regex.Pattern

/** User to search text spans by pattern in a text. */
abstract class BaseSpanFactory : SpanFactory {
    protected abstract val searchPattern: Pattern
    protected abstract val validatePattern: Pattern

    override fun search(text: CharSequence): Array<OmniSpan> {
        val matcher = searchPattern.matcher(text)
        val result = mutableListOf<OmniSpan>()
        var searchStart = 0
        while (searchStart <= text.length && matcher.find(searchStart)) {
            val start = matcher.start(0)
            val end = matcher.end(0)
            buildOf(text, start, end)?.let {
                result.add(it)
            }
            searchStart = end + 1
        }
        return result.toTypedArray()
    }
}