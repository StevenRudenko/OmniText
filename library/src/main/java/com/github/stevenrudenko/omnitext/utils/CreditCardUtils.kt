package com.github.stevenrudenko.omnitext.utils

/** Credit cards utilities. */
internal object CreditCardUtils {

    fun isValidCreditCardNumber(cardNumber: String): Boolean {
        val cardIntArray = cardNumber.toCharArray().map { ("" + it).toInt() }.toIntArray()
        var i = cardIntArray.size - 2
        while (i >= 0) {
            var num = cardIntArray[i] * 2
            if (num > 9) {
                num = num % 10 + num / 10
            }
            cardIntArray[i] = num
            i -= 2
        }

        val sum = cardIntArray.sum()
        return sum % 10 == 0
    }

}