package com.fourlastor.dante.html;

import com.fourlastor.dante.parser.Block;

class HtmlBlock implements Block {
    private String name;

    HtmlBlock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
