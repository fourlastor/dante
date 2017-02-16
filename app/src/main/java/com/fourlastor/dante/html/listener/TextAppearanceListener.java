package com.fourlastor.dante.html.listener;

import android.content.Context;
import android.text.style.TextAppearanceSpan;

public class TextAppearanceListener extends BlockStyleListener {

    private final TextAppearanceSpan span;

    public TextAppearanceListener(Context context, int appearance, String... tags) {
        super(tags);
        this.span = new TextAppearanceSpan(context, appearance);
    }

    @Override
    protected Object getStyleSpan() {
        return span;
    }
}
