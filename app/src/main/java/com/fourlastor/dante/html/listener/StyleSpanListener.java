package com.fourlastor.dante.html.listener;

import android.text.style.StyleSpan;

public class StyleSpanListener extends BlockStyleListener {

    private int style;

    public StyleSpanListener(int style, String... tags) {
        super(tags);
        this.style = style;
    }

    @Override
    protected Object getStyleSpan() {
        return new StyleSpan(style);
    }
}
