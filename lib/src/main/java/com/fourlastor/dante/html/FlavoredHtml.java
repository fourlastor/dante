package com.fourlastor.dante.html;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.annotation.StyleRes;
import android.text.Spanned;

import com.fourlastor.dante.Dante;

public class FlavoredHtml {

    private final Dante dante;

    private FlavoredHtml(Dante dante) {
        this.dante = dante;
    }

    public static class Builder {

        private final Context context;
        private final Dante dante;

        public Builder(Context context) {
            this.context = context;
            this.dante = new Dante(new HtmlParser());
        }

        public Builder newLine(String... tags) {
            dante.register(new NewLineListener(tags));
            return this;
        }

        public Builder textAppearance(@StyleRes int styleRes, String... tags) {
            dante.register(new TextAppearanceListener(context, styleRes, tags));
            return this;
        }

        @IntDef({Typeface.NORMAL, Typeface.BOLD, Typeface.BOLD_ITALIC, Typeface.ITALIC})
        @interface TypefaceInt {

        }

        public Builder style(@TypefaceInt int typeface, String... tags) {
            dante.register(new StyleSpanListener(typeface, tags));
            return this;
        }

        public Builder bullet(int margin, String... tags) {
            dante.register(new LeadingMarginSpanListener(margin, tags));
            dante.register(new BulletSpanListener(margin, tags));
            return this;
        }

        public Builder img(ImgListener.ImgGetter imgGetter) {
            dante.register(new ImgListener(imgGetter));
            return this;
        }

        public FlavoredHtml build() {
            return new FlavoredHtml(dante);
        }
    }

    public Spanned parse(String html) {
        return dante.parse(html);
    }
}
