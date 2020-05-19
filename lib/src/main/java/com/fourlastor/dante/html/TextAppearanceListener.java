package com.fourlastor.dante.html;

import android.content.Context;
import android.text.style.TextAppearanceSpan;

class TextAppearanceListener extends BlockStyleListener {

    private final Context context;
    private final int appearance;

    TextAppearanceListener(Context context, int appearance, String... tags) {
        super(tags);
        this.context = context;
        this.appearance = appearance;
    }

    @Override
    protected Object getStyleSpan() {
        return new TextAppearanceSpan(context, appearance);
    }
}
