package com.fourlastor.dante.html;

import android.text.style.StyleSpan;

class StyleSpanListener extends BlockStyleListener {

    private int style;

    StyleSpanListener(int style, String... tags) {
        super(tags);
        this.style = style;
    }

    @Override
    protected Object getStyleSpan() {
        return new StyleSpan(style);
    }
}
