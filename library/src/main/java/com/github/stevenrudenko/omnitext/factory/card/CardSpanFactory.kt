package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.OmniSpan
import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.BaseSpanFactory
import com.github.stevenrudenko.omnitext.utils.CreditCardUtils
import java.util.regex.Pattern

/** User to search credit card spans in a text. */
abstract class CardSpanFactory : BaseSpanFactory() {
    protected abstract val type: Int

    protected abstract val validIconId: Int
    protected abstract val invalidIconId: Int

    override val searchPattern: Pattern
        get() = PATTERN

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan? {
        val part = text.subSequence(start, end)
        val minified = minify(part)
        val cardMatcher = validatePattern.matcher(minified)
        if (!cardMatcher.matches()) {
            return null
        }
        val prettified = prettify(part)
        val valid = validate(part)
        return OmniSpan(
            type,
            start,
            end,
            text = prettified,
            valid,
            icon = if (valid) validIconId else invalidIconId
        )
    }

    override fun validate(text: CharSequence): Boolean {
        val minified = minify(text).toString()
        return CreditCardUtils.isValidCreditCardNumber(minified)
    }

    override fun prettify(text: CharSequence): CharSequence = minify(text).chunked(4)
        .reduce { acc, part -> if (acc.isEmpty()) part else "$acc $part" }

    override fun minify(text: CharSequence): CharSequence = text.toString()
        .replace(" ", "")
        .replace("-", "")

    companion object {
        private var PATTERN = Pattern.compile(
            "\\b(?:\\d[ -]*?){13,16}\\b"
        )
    }
}