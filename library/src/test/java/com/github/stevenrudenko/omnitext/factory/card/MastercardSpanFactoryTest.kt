package com.github.stevenrudenko.omnitext.factory.card

import com.github.stevenrudenko.omnitext.R
import com.github.stevenrudenko.omnitext.factory.SpanFactory.Companion.TYPE_MASTERCARD
import com.github.stevenrudenko.omnitext.OmniSpan
import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [MastercardSpanFactory].
 */
internal class MastercardSpanFactoryTest {

    val parser = MastercardSpanFactory()

    @Test
    fun testSearch_empty() {
        val result = parser.search(TEXT_NO_CARDS)
        assertArrayEquals(emptyArray(), result)
    }

    @Test
    fun testSearch_results() {
        val result = parser.search(TEXT)
        val expected = arrayOf(
            OmniSpan(TYPE_MASTERCARD, 25, 25 + 19, VALID_PRETTIFIED, true, R.drawable.omni_card_mc_valid),
            OmniSpan(TYPE_MASTERCARD, 98, 98 + 19, VALID_PRETTIFIED, true, R.drawable.omni_card_mc_valid),
        )
        assertArrayEquals(expected, result)
    }

    @Test
    fun testBuildOf() {
        val start = 25
        val end = start + 19
        val expected =
            OmniSpan(TYPE_MASTERCARD, start, end, VALID_PRETTIFIED, true, R.drawable.omni_card_mc_valid)
        val result = parser.buildOf(TEXT, 25, 25 + 19)
        assertEquals(expected, result)
    }

    @Test
    fun testValidate_valid() {
        assertTrue(parser.validate(VALID_DASHES))
    }

    @Test
    fun testValidate_invalid() {
        assertFalse(parser.validate(INVALID))
    }

    @Test
    fun testPrettify_spaces() {
        assertEquals(VALID_PRETTIFIED, parser.prettify(VALID_SPACES))
    }

    @Test
    fun testPrettify_dashes() {
        assertEquals(VALID_PRETTIFIED, parser.prettify(VALID_DASHES))
    }

    @Test
    fun testPrettify_invalid() {
        assertEquals(INVALID_PRETTIFIED, parser.prettify(INVALID))
    }

    @Test
    fun testMinify_spaces() {
        assertEquals(VALID_MINIFIED, parser.minify(VALID_SPACES))
    }

    @Test
    fun testMinify_dashes() {
        assertEquals(VALID_MINIFIED, parser.minify(VALID_DASHES))
    }

    @Test
    fun testMinify_invalid() {
        assertEquals(INVALID_MINIFIED, parser.minify(INVALID))
    }

    companion object {
        private const val VALID_MINIFIED = "5200828282828210"
        private const val VALID_PRETTIFIED = "5200 8282 8282 8210"
        private const val VALID_SPACES = "5200 8282 8282 8210"
        private const val VALID_DASHES = "5200-8282-8282-8210"
        private const val INVALID = "5200-8272-8282-8210"

        private const val INVALID_MINIFIED = "5200827282828210"
        private const val INVALID_PRETTIFIED = "5200 8272 8282 8210"

        private const val TEXT_NO_CARDS =
            "The test test with card # pasted in a text\nMultiline text support is also made"
        private const val TEXT =
            "The test test with card #$VALID_DASHES pasted in a text\nMultiline text support is also made $VALID_SPACES"
    }
}