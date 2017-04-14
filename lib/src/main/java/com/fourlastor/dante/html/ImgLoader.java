package com.fourlastor.dante.html;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public interface ImgLoader {

    Drawable loadImage(@NonNull String src);

    abstract class BitmapLoader implements ImgLoader {

        private final Resources resources;

        protected BitmapLoader(Resources resources) {
            this.resources = resources;
        }

        @Override
        public Drawable loadImage(@NonNull String src) {
            Bitmap bitmap = loadBitmap(src);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);

            bitmapDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            return bitmapDrawable;
        }

        protected abstract Bitmap loadBitmap(String src);
    }
}
