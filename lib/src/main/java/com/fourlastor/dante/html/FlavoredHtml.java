package com.fourlastor.dante.html;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.annotation.StyleRes;
import android.text.Spanned;

import com.fourlastor.dante.FlavoredTextBuilder;

public class FlavoredHtml {

    private final FlavoredTextBuilder builder;

    private FlavoredHtml(FlavoredTextBuilder builder) {
        this.builder = builder;
    }

    public static class Builder {

        private final Context context;
        private final FlavoredTextBuilder builder;

        public Builder(Context context) {
            this.context = context;
            this.builder = new FlavoredTextBuilder(new HtmlParser());
        }

        public Builder newLine(String... tags) {
            builder.register(new NewLineListener(tags));
            return this;
        }

        public Builder textAppearance(@StyleRes int styleRes, String... tags) {
            builder.register(new TextAppearanceListener(context, styleRes, tags));
            return this;
        }

        @IntDef({Typeface.NORMAL, Typeface.BOLD, Typeface.BOLD_ITALIC, Typeface.ITALIC})
        @interface TypefaceInt {

        }

        public Builder style(@TypefaceInt int typeface, String... tags) {
            builder.register(new StyleSpanListener(typeface, tags));
            return this;
        }

        public Builder bullet(int margin, String... tags) {
            builder.register(new LeadingMarginSpanListener(margin, tags));
            builder.register(new BulletSpanListener(margin, tags));
            return this;
        }

        public FlavoredHtml build() {
            return new FlavoredHtml(builder);
        }
    }

    public Spanned parse(String html) {
        return builder.parse(html);
    }
}
