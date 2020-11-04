package com.github.stevenrudenko.omnitext

import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.github.stevenrudenko.omnitext.factory.*
import com.github.stevenrudenko.omnitext.factory.card.*
import com.github.stevenrudenko.omnitext.factory.generic.NumberSpanFactory
import com.github.stevenrudenko.omnitext.factory.generic.PhoneSpanFactory
import com.github.stevenrudenko.omnitext.factory.generic.TextSpanFactory

/** Text view with omni action implementation, */
open class OmniTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var factory: OmniTextSpannableFactory
    var onOmniAction: ((OmniSpan) -> Unit)? = null
        set(value) {
            factory.listener = value
            field = value
            text = text.toString()
        }
        get() = factory.listener

    var omniEnabled: Boolean = true
        set(value) {
            factory.enabled = value
            field = value
            text = text.toString()
        }
        get() = factory.enabled

    init {
        factory = createSpanFactory(
            TextSpanFactory(),
            NumberSpanFactory(),
            PhoneSpanFactory(context),
            AmericanExpressSpanFactory(),
            DinersCardSpanFactory(),
            DiscoverSpanFactory(),
            JcbSpanFactory(),
            MastercardSpanFactory(),
            VisaSpanFactory()
        )

        highlightColor = Color.TRANSPARENT
        setSpannableFactory(factory)
        movementMethod = LinkMovementMethod.getInstance();
    }

    private fun createSpanFactory(vararg factory: SpanFactory): OmniTextSpannableFactory {
        return OmniTextSpannableFactory(
            context,
            factory
        )
    }

}