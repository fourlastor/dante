package com.fourlastor.dante.html.listener;

import android.graphics.Typeface;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;

public class TypefaceListener extends BlockStyleListener {
    private Typeface typeface;

    public TypefaceListener(Typeface typeface, String... tags) {
        super(tags);
        this.typeface = typeface;
    }

    @Override
    protected Object getStyleSpan() {
        return new CalligraphyTypefaceSpan(typeface);
    }
}
