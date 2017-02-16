package com.fourlastor.dante;

import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.fourlastor.dante.parser.Block;
import com.fourlastor.dante.parser.BlockListener;
import com.fourlastor.dante.parser.ParseListener;
import com.fourlastor.dante.parser.Parser;

import java.util.ArrayList;

public class FlavoredTextBuilder implements ParseListener {
    private final Parser parser;
    private final ArrayList<BlockListener> listeners;
    private SpannableStringBuilder builder;

    public FlavoredTextBuilder(Parser parser) {
        this.parser = parser;
        this.listeners = new ArrayList<>();
        parser.register(this);
    }

    public Spanned parse(String string) {
        builder = new SpannableStringBuilder();
        parser.parse(string);

        return builder;
    }

    @Override public void characters(String string) {
        builder.append(string);
    }

    @Override public void start(Block block) {
        for (BlockListener listener: listeners) {
            if (listener.match(block)) {
                listener.start(block, builder);
            }
        }
    }

    @Override public void end(Block block) {
        for (BlockListener listener: listeners) {
            if (listener.match(block)) {
                listener.end(block, builder);
            }
        }
    }

    public void register(BlockListener listener) {
        this.listeners.add(listener);
    }
}
