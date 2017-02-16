package com.fourlastor.dante.html;

import android.content.Context;
import android.text.style.TextAppearanceSpan;

class TextAppearanceListener extends BlockStyleListener {

    private final TextAppearanceSpan span;

    TextAppearanceListener(Context context, int appearance, String... tags) {
        super(tags);
        this.span = new TextAppearanceSpan(context, appearance);
    }

    @Override
    protected Object getStyleSpan() {
        return span;
    }
}
