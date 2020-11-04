package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.OmniSpan
import com.github.stevenrudenko.omnitext.factory.BaseSpanFactory
import com.github.stevenrudenko.omnitext.utils.CreditCardUtils

/** User to search credit card spans in a text. */
abstract class CardSpanFactory : BaseSpanFactory() {
    protected abstract val type: Int

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val part = text.subSequence(start, end)
        val prettified = prettify(part)
        val valid = validate(part)
        return OmniSpan(type, start, end, prettified, valid)
    }

    override fun validate(text: CharSequence): Boolean {
        val minified = minify(text).toString()
        return CreditCardUtils.isValidCreditCardNumber(minified)
    }

    override fun prettify(text: CharSequence): CharSequence = minify(text).chunked(4)
        .reduce { acc, part -> if (acc.isEmpty()) part else "$acc $part" }

    override fun minify(text: CharSequence): CharSequence = text.toString().replace(" ", "")
        .replace("-", "")

}