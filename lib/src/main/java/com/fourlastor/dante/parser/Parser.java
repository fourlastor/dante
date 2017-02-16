package com.fourlastor.dante.parser;

public interface Parser {
    public void parse(String string);
    public void register(ParseListener listener);
}
