package com.fourlastor.dante.html;

import android.text.style.LeadingMarginSpan;

class LeadingMarginSpanListener extends BlockStyleListener {

    private final int margin;

    LeadingMarginSpanListener(int margin, String... tags) {
        super(tags);
        this.margin = margin;
    }

    @Override
    protected Object getStyleSpan() {
        return new LeadingMarginSpan.Standard(margin, 0);
    }
}
