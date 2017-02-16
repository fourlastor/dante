package com.fourlastor.dante.parser;

public interface Parser {
    void parse(String string);
    void register(ParseListener listener);
}
