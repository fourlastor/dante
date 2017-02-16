package com.fourlastor.dante.html;

import android.text.SpannableStringBuilder;

import com.fourlastor.dante.parser.Block;
import com.fourlastor.dante.parser.BlockListener;

import java.util.Arrays;
import java.util.List;

class NewLineListener implements BlockListener {

    private List<String> tags;

    NewLineListener(String... tags) {
        this.tags = Arrays.asList(tags);
    }

    @Override
    public void start(Block block, SpannableStringBuilder text) {
        addNewLine(text);
    }

    @Override
    public void end(Block block, SpannableStringBuilder text) {
        addNewLine(text);
    }

    private void addNewLine(SpannableStringBuilder text) {
        final int len = text.length();
        if (len >= 1 && text.charAt(len - 1) == '\n') {
            return;
        }

        if (len != 0) {
            text.append("\n");
        }
    }

    @Override
    public boolean match(Block block) {
        return
                block instanceof HtmlBlock
                        && matchName(((HtmlBlock) block).getName());
    }

    private boolean matchName(String name) {
        return tags.contains(name);
    }
}
