package com.github.stevenrudenko.omnitext

data class OmniSpan(
    val type: Int,
    val start: Int,
    val end: Int,
    val text: CharSequence,
    val valid: Boolean = true,
    val icon: Int = 0
)