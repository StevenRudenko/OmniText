package com.github.stevenrudenko.omnitext.factory

import com.github.stevenrudenko.omnitext.OmniSpan

/** User to search spans in a text. */
interface SpanFactory {

    fun search(text: CharSequence): Array<OmniSpan>

    fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan?

    fun validate(text: CharSequence): Boolean

    fun prettify(text: CharSequence): CharSequence

    fun minify(text: CharSequence): CharSequence

    companion object {
        const val TYPE_TEXT = 1
        const val TYPE_EMAIL = 2
        const val TYPE_URL = 3
        const val TYPE_PHONE = 4
        const val TYPE_VISA = 21
        const val TYPE_MASTERCARD = 22
        const val TYPE_AM_EXPRESS = 23
        const val TYPE_DINERS_CLUB = 24
        const val TYPE_DISCOVERY = 25
        const val TYPE_JCB = 26
        const val TYPE_NUMBER = Int.MAX_VALUE
    }
}