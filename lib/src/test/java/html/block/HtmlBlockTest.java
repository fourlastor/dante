package html.block;

import junit.framework.TestCase;

import fm.pause.stringparty.html.block.HtmlBlock;

public class HtmlBlockTest extends TestCase {
    public void testAssignBlockName() throws Exception {
        HtmlBlock block = new HtmlBlock("p");

        assertEquals("p", block.getName());
    }
}
