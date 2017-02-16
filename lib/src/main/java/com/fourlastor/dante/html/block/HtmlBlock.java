package com.fourlastor.dante.html.block;

import com.fourlastor.dante.parser.Block;

public class HtmlBlock implements Block {
    private String name;

    public HtmlBlock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
