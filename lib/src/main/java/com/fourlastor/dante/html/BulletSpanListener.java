package com.fourlastor.dante.html;

import android.text.style.BulletSpan;

class BulletSpanListener extends BlockStyleListener {

    private final int margin;

    BulletSpanListener(int margin, String... tags) {
        super(tags);
        this.margin = margin;
    }

    @Override
    protected Object getStyleSpan() {
        return new BulletSpan(margin);
    }
}
