package com.fourlastor.dante.parser;

public interface ParseListener {
    public void characters(String string);
    void start(Block block);
    void end(Block block);
}
