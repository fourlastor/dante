package com.fourlastor.dante.html;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

    public static abstract class BitmapImgGetter implements ImgGetter {

        private final Resources resources;

        protected BitmapImgGetter(Resources resources) {
            this.resources = resources;
        }

        @Override
        public Drawable getImage(@NonNull String src) {
            Bitmap bitmap = getBitmap(src);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);

            bitmapDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            return bitmapDrawable;
        }

        protected abstract Bitmap getBitmap(String src);
    }
}
