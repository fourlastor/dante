package com.fourlastor.dante.parser;

import android.text.SpannableStringBuilder;

public interface BlockListener {
    void start(Block block, SpannableStringBuilder text);
    void end(Block block, SpannableStringBuilder text);
    boolean match(Block block);
}
