package com.github.stevenrudenko.omnitext

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.core.content.res.ResourcesCompat
import com.github.stevenrudenko.omnitext.factory.SpanFactory
import com.google.android.material.chip.ChipDrawable

/** Used to parse and render within [Spannable]. */
class OmniTextSpannableFactory(
    private val context: Context,
    private val factories: Array<out SpanFactory>
) : Spannable.Factory() {
    var enabled: Boolean = true
    var listener: ((OmniSpan) -> Unit)? = null

    override fun newSpannable(source: CharSequence?): Spannable {
        if (!enabled) {
            return super.newSpannable(source)
        }

        val input = source ?: return source as Spannable
        val spans = search(input)
        val spannable = SpannableString(input)
        spans.forEach {
            val chip = createChipSpan(it)
            spannable.setSpan(
                ActionSpan(it),
                it.start,
                it.end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(chip, it.start, it.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannable
    }

    private fun createChipSpan(span: OmniSpan): ImageSpan {
        val chip = ChipDrawable.createFromResource(context, R.xml.omni_chip)
        chip.text = span.text
        chip.chipIcon = if (span.icon > 0)
            ResourcesCompat.getDrawable(context.resources, span.icon, context.theme)
        else null
        chip.setBounds(0, 0, chip.intrinsicWidth, chip.intrinsicHeight)
        return ImageSpan(chip)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun search(text: CharSequence): Array<OmniSpan> {
        return filter(factories.map { it.search(text) }
            .reduce { acc, spans -> acc.plus(spans) })
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun filter(spans: Array<OmniSpan>): Array<OmniSpan> {
        return spans.filter { src ->
            val other = spans.filter { it.valid && src.start >= it.start && src.end <= it.end }
            if (other.isEmpty()) true
            else if (!src.valid) false
            else other.find { it.type < src.type } == null
        }.toTypedArray()
    }

    inner class ActionSpan(
        private val span: OmniSpan
    ) : ClickableSpan() {
        override fun onClick(v: View) {
            listener?.invoke(span)
        }
    }
}