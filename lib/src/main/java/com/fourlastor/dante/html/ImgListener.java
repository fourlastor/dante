package com.fourlastor.dante.html;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import com.fourlastor.dante.parser.Block;
import com.fourlastor.dante.parser.BlockListener;

class ImgListener implements BlockListener {

    private static final String UNICODE_REPLACE = "\uFFFC";
    private static final String TAG_IMG = "img";
    private static final String ATTRIBUTE_SRC = "src";

    private final ImgLoader imgLoader;

    ImgListener(ImgLoader imgLoader) {
        this.imgLoader = imgLoader;
    }

    @Override
    public void start(Block block, SpannableStringBuilder text) {
        String src = ((HtmlBlock) block).getAttributes().get(ATTRIBUTE_SRC);
        if (src == null) {
            return;
        }

        int originalLength = text.length();
        text.append(UNICODE_REPLACE);
        Drawable image = imgLoader.loadImage(src);
        text.setSpan(
                new ImageSpan(image, src),
                originalLength,
                text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
    }

    @Override
    public void end(Block block, SpannableStringBuilder text) {
        // nothing to do here
    }

    @Override
    public boolean match(Block block) {
        return block instanceof HtmlBlock
                && TAG_IMG.equalsIgnoreCase(((HtmlBlock) block).getName());
    }
}
