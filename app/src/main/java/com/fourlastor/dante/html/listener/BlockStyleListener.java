package com.fourlastor.dante.html.listener;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.fourlastor.dante.html.block.HtmlBlock;
import com.fourlastor.dante.parser.Block;
import com.fourlastor.dante.parser.BlockListener;

import java.util.Arrays;
import java.util.List;

public abstract class BlockStyleListener implements BlockListener {

    private List<String> tags;

    public BlockStyleListener(String... tags) {
        this.tags = Arrays.asList(tags);
    }

    @Override
    public void start(Block block, SpannableStringBuilder text) {
        final int len = text.length();
        text.setSpan(this, len, len, Spannable.SPAN_MARK_MARK);
    }

    @Override
    public void end(Block block, SpannableStringBuilder text) {
        final int len = text.length();
        Object obj = getLast(text, BlockStyleListener.class);
        int start = text.getSpanStart(obj);

        text.removeSpan(obj);

        if (start < 0) {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder
                    .append("Start is < 0 in block with text: ")
                    .append(text.toString())
                    .append(" tags:");

            for (int i = 0; i < tags.size(); i++) {
                messageBuilder.append(" %s");
            }

            String message = messageBuilder.toString();
//            Timber.e(message, tags.toArray());
            return;
        }

        if (start != len) {
            text.setSpan(getStyleSpan(), start, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    protected abstract Object getStyleSpan();

    private Object getLast(SpannableStringBuilder text, Class kind) {
        /*
         * This knows that the last returned object from getSpans()
         * will be the most recently added.
         */
        Object[] objs = text.getSpans(0, text.length(), kind);

        if (objs.length == 0) {
            return null;
        } else {
            return objs[objs.length - 1];
        }
    }

    @Override
    public boolean match(Block block) {
        return block instanceof HtmlBlock
                && tags.contains(((HtmlBlock) block).getName());
    }
}
