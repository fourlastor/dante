package com.fourlastor.dante.html;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import com.fourlastor.dante.parser.Block;
import com.fourlastor.dante.parser.BlockListener;

public class ImgListener implements BlockListener {

    private static final String UNICODE_REPLACE = "\uFFFC";

    private final ImgGetter imgGetter;

    ImgListener(ImgGetter imgGetter) {
        this.imgGetter = imgGetter;
    }

    @Override
    public void start(Block block, SpannableStringBuilder text) {
        String src = ((HtmlBlock) block).getAttributes().get("src");
        if (src == null) {
            return;
        }

        int len = text.length();
        text.append(UNICODE_REPLACE);
        Drawable image = imgGetter.getImage(src);
        text.setSpan(
                new ImageSpan(image, src),
                len,
                text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
    }

    @Override
    public void end(Block block, SpannableStringBuilder text) {

    }

    @Override
    public boolean match(Block block) {
        return block instanceof HtmlBlock
                && "img".equalsIgnoreCase(((HtmlBlock) block).getName());
    }

    public interface ImgGetter {

        Drawable getImage(@NonNull String src);
    }
}
