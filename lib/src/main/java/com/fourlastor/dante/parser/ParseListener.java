package com.fourlastor.dante.parser;

public interface ParseListener {
    void characters(String string);
    void start(Block block);
    void end(Block block);
}
