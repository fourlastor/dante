package com.fourlastor.dante.html;

import com.fourlastor.dante.parser.Block;

import java.util.Map;

class HtmlBlock implements Block {

    private final String name;
    private final Map<String, String> attributes;

    HtmlBlock(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
