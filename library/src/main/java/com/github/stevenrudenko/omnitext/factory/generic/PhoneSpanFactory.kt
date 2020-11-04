package com.github.stevenrudenko.omnitext.factory.generic

import android.content.Context
import android.telephony.PhoneNumberUtils
import android.telephony.TelephonyManager
import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_PHONE
import com.github.stevenrudenko.omnitext.OmniSpan
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

/** User to search phone number spans in a text. */
open class PhoneSpanFactory(
    context: Context
) : SpanFactory {

    private val countryIso: String
    private val utils = PhoneNumberUtil.getInstance()

    init {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        countryIso = telephonyManager.networkCountryIso.toUpperCase()
    }

    override fun search(text: CharSequence): Array<OmniSpan> =
        utils.findNumbers(text, countryIso)
            .map { buildOf(it.rawString(), it.start(), it.end()) }
            .toTypedArray()

    override fun buildOf(text: CharSequence, start: Int, end: Int): OmniSpan {
        val part = text
        val prettified = prettify(part)
        val valid = validate(part)
        val icon = R.drawable.omni_phone
        return OmniSpan(
            TYPE_PHONE,
            start,
            end,
            prettified,
            valid,
            icon
        )
    }

    override fun validate(text: CharSequence): Boolean = utils.isValidNumber(parseNumber(text))

    override fun prettify(text: CharSequence): CharSequence =
        PhoneNumberUtils.formatNumber(text.toString(), countryIso)

    override fun minify(text: CharSequence): CharSequence = text

    private fun parseNumber(text: CharSequence): Phonenumber.PhoneNumber =
        utils.parse(text, countryIso)

}