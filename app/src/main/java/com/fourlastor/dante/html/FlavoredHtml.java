package com.fourlastor.dante.html;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;

import com.fourlastor.dante.FlavoredTextBuilder;
import com.fourlastor.dante.R;
import com.fourlastor.dante.html.listener.BlockStyleListener;
import com.fourlastor.dante.html.listener.NewLineListener;
import com.fourlastor.dante.html.listener.StyleSpanListener;
import com.fourlastor.dante.html.listener.TextAppearanceListener;

import java.util.Map;
import java.util.WeakHashMap;

public class FlavoredHtml {

    private static FlavoredTextBuilder builder;

    private static final Map<String, Spanned> CACHE = new WeakHashMap<>();

    private FlavoredHtml() {
    }

    public static Spanned fromHtml(Context context, String html) {
        Spanned spanned = CACHE.get(html);

        if (spanned == null) {
            spanned = getInstance(context).parse(html);
            CACHE.put(html, spanned);
        }
        return spanned;
    }

    private synchronized static FlavoredTextBuilder getInstance(Context context) {
        if (builder == null) {
            builder = new FlavoredTextBuilder(new HtmlParser());

            builder.register(new NewLineListener("p", "h1", "h2", "h3", "h4", "h5", "h6", "li"));
            builder.register(new TextAppearanceListener(context, R.style.headline, "h1"));
            builder.register(new TextAppearanceListener(context, R.style.title, "h2"));
            builder.register(new TextAppearanceListener(context, R.style.subhead, "h3"));
            builder.register(new TextAppearanceListener(context, R.style.body, "p", "li"));

            builder.register(new StyleSpanListener(Typeface.BOLD, "b", "strong"));
            builder.register(new StyleSpanListener(Typeface.ITALIC, "i", "em"));
            final int bulletMargin = 15;
            builder.register(new LeadingMarginSpanListener(bulletMargin, "li"));
            builder.register(new BulletSpanListener(bulletMargin, "li"));
        }

        return builder;
    }

    private static class BulletSpanListener extends BlockStyleListener {

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

    private static class LeadingMarginSpanListener extends BlockStyleListener {

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
}
