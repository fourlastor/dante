package com.fourlastor.dante.html;

import com.fourlastor.dante.html.block.HtmlBlock;
import com.fourlastor.dante.parser.ParseListener;
import com.fourlastor.dante.parser.Parser;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;


public class HtmlParser implements Parser, ContentHandler {

    ParseListener listener;
    StringBuilder buffer;

    @Override public void parse(String string) {
        org.ccil.cowan.tagsoup.Parser parser = new org.ccil.cowan.tagsoup.Parser();
        parser.setContentHandler(this);
        try {
            parser.parse(new InputSource(new StringReader(string)));
        } catch (IOException | SAXException e) {
            // We are reading from a string. There should not be IO problems.
            // TagSoup doesn't throw parse exceptions.
            throw new RuntimeException(e);
        }

        emptyBuffer();
    }

    private void emptyBuffer() {
        if (buffer != null) {
            String normalizedBuffer = buffer.toString().replaceAll("\\s+", " ");
            buffer = null;
            listener.characters(normalizedBuffer);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        emptyBuffer();
        listener.start(new HtmlBlock(localName));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        emptyBuffer();
        listener.end(new HtmlBlock(localName));
    }

    @Override public void characters(char[] ch, int start, int length) throws SAXException {
        if (buffer == null) {
            buffer = new StringBuilder();
        }

        buffer.append(ch, start, length);
    }

    @Override public void register(ParseListener listener) {
        this.listener = listener;
    }

    @Override public void setDocumentLocator(Locator locator) {}

    @Override public void startDocument() throws SAXException {}

    @Override public void endDocument() throws SAXException {}

    @Override public void startPrefixMapping(String prefix, String uri) throws SAXException {}

    @Override public void endPrefixMapping(String prefix) throws SAXException {}

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}

    @Override public void processingInstruction(String target, String data) throws SAXException {}

    @Override public void skippedEntity(String name) throws SAXException {}
}
