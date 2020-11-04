package com.github.stevenrudenko.omnitext.factory.card

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

/**
 * Test for [DiscoverSpanFactory].
 */
class DiscoverSpanFactoryTest : TestCase() {

    val parser = DiscoverSpanFactory()

    @Test
    fun testValidate_valid() {
        Assert.assertTrue(parser.validate(VALID))
    }

    @Test
    fun testValidate_invalid() {
        Assert.assertFalse(parser.validate(INVALID))
    }

    companion object {
        private const val VALID = "6011000990139424"
        private const val INVALID = "6011010990139424"
    }
}