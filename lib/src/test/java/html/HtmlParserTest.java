package html;

import junit.framework.TestCase;

import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mockito;

import fm.pause.stringparty.html.HtmlParser;
import fm.pause.stringparty.html.block.HtmlBlock;
import fm.pause.stringparty.parser.Block;
import fm.pause.stringparty.parser.ParseListener;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

public class HtmlParserTest extends TestCase {
    private HtmlParser parser;
    private ParseListener listener;

    @Override public void setUp() throws Exception {
        super.setUp();

        parser = new HtmlParser();
        listener = Mockito.mock(ParseListener.class);
        parser.register(listener);
    }

    public void testReturnsCorrectText() throws Exception {
        parser.parse("hello world");

        verify(listener).characters("hello world");
    }

    public void testDispatchesCorrectBlocks() throws Exception {
        parser.parse("<p>abc</p>");

        verify(listener).start(isHtmlBlock("p"));
        verify(listener).characters("abc");
        verify(listener).end(isHtmlBlock("p"));
    }

    public void testDispatchesCorrectlyNestedBlocks() throws Exception {
        parser.parse("<p><b>abc</b></p>");

        InOrder inOrder = inOrder(listener);

        inOrder.verify(listener).start(isHtmlBlock("p"));
        inOrder.verify(listener).start(isHtmlBlock("b"));
        inOrder.verify(listener).characters("abc");
        inOrder.verify(listener).end(isHtmlBlock("b"));
        inOrder.verify(listener).end(isHtmlBlock("p"));
    }

    public void testCharactersSentInChunks() throws Exception {
        String input = "imaverylongbutverylongstringthatmightbesentinchunks" +
                "imaverylongbutverylongstringthatmightbesentinchunks" +
                "imaverylongbutverylongstringthatmightbesentinchunks" +
                "imaverylongbutverylongstringthatmightbesentinchunks" +
                "imaverylongbutverylongstringthatmightbesentinchunks" +
                "imaverylongbutverylongstringthatmightbesentinchunks" +
                "imaverylongbutverylongstringthatmightbesentinchunks";

        final StringBuilder result = new StringBuilder();

        parser.register(new ParseListener() {
            @Override public void characters(String string) {
                result.append(string);
            }

            @Override public void start(Block block) {}

            @Override public void end(Block block) {}
        });

        parser.parse(input);
        assertEquals(input, result.toString());
    }

    public void testWhiteSpaceIsSpace() throws Exception {
        parser.parse("Hello  great\nworld\tadieu");
        verify(listener).characters("Hello great world adieu");
    }

    private HtmlBlock isHtmlBlock(String name) {
        return argThat(new IsHtmlBlock(name));
    }

    private static class IsHtmlBlock extends ArgumentMatcher<HtmlBlock> {

        private String name;

        private IsHtmlBlock(String name) {
            this.name = name;
        }

        @Override public boolean matches(Object block) {
            return ((HtmlBlock) block).getName().equals(name);
        }
    }
}
