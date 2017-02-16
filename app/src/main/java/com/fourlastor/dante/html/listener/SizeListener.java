package com.fourlastor.dante.html.listener;

import android.text.style.AbsoluteSizeSpan;

public class SizeListener extends BlockStyleListener {
    private final int size;

    public SizeListener(int size, String... tags) {
        super(tags);

        this.size = size;
    }

    @Override
    protected Object getStyleSpan() {
        return new AbsoluteSizeSpan(size, true);
    }
}
