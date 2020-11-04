package com.github.stevenrudenko.omnitext.factory

import com.github.stevenrudenko.omnitext.OmniSpan

/** User to search spans in a text. */
interface SpanFactory {

    fun search(text: CharSequence): Array<OmniSpan>

    fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan

    fun validate(text: CharSequence): Boolean

    fun prettify(text: CharSequence): CharSequence

    fun minify(text: CharSequence): CharSequence

    companion object {
        const val TYPE_TEXT = 1
        const val TYPE_PHONE = 2
        const val TYPE_VISA = 3
        const val TYPE_MASTERCARD = 4
        const val TYPE_AM_EXPRESS = 5
        const val TYPE_DINERS_CLUB = 6
        const val TYPE_DISCOVERY = 7
        const val TYPE_JCB = 8
        const val TYPE_NUMBER = Int.MAX_VALUE
    }
}