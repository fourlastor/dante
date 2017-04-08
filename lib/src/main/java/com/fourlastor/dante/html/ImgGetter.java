package com.fourlastor.dante.html;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public interface ImgGetter {

    Drawable getImage(@NonNull String src);

    abstract class BitmapImgGetter implements ImgGetter {

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
