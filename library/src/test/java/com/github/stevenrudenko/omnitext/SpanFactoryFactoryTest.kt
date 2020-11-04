package com.github.stevenrudenko.uncut.parser

import android.content.Context
import com.github.stevenrudenko.omnitext.OmniTextSpannableFactory
import com.github.stevenrudenko.omnitext.OmniSpan
import junit.framework.TestCase
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.mockito.Mockito.mock

/**
 *
 */
class SpanFactoryFactoryTest : TestCase() {

    private lateinit var factory: OmniTextSpannableFactory

    @Before
    override fun setUp() {
        factory = OmniTextSpannableFactory(
            mock(Context::class.java),
            emptyArray(),
        )
    }

    fun testFilter() {
        val invalid = OmniSpan(1, start=0, end=19, text="4149 4391 0255 9584", icon=1, valid=false)
        val valid = OmniSpan(2, start=0, end=19, text="4149 4391 0255 9584", icon=2, valid=true)
        val other = OmniSpan(3, start=5, end=35, text="4149 4391 0255 9584", icon=3, valid=true)
        val expected = arrayOf(valid, other)
        val result = factory.filter(arrayOf(invalid, valid, other))
        assertArrayEquals(expected, result)
    }
}