import android.text.SpannableStringBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fm.pause.stringparty.FlavoredTextBuilder;
import fm.pause.stringparty.parser.Block;
import fm.pause.stringparty.parser.BlockListener;
import fm.pause.stringparty.parser.ParseListener;
import fm.pause.stringparty.parser.Parser;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class FlavoredTextBuilderTest {

    @Test public void testRegistersOnParser() throws Exception {
        Parser parser = Mockito.mock(Parser.class);
        FlavoredTextBuilder builder = new FlavoredTextBuilder(parser);
        verify(parser).register(builder);
    }

    @Test public void testAddsCharactersFromParser() throws Exception {
        FlavoredTextBuilder builder = new FlavoredTextBuilder(new MockParser());

        assertThat(builder.parse("").toString(), is("abc"));
    }

    @Test public void testCallsStartOnMatchingListener() throws Exception {
        FlavoredTextBuilder builder = new FlavoredTextBuilder(new MockParser());

        final BlockListener listener = Mockito.mock(BlockListener.class);
        builder.register(listener);
        when(listener.match(any(MockBlock.class))).thenReturn(true);

        builder.parse("");

        verify(listener).start(isA(MockBlock.class), isA(SpannableStringBuilder.class));
    }

    @Test public void testDoesNotCallStartOnNotMatchingListener() throws Exception {
        FlavoredTextBuilder builder = new FlavoredTextBuilder(new MockParser());

        final BlockListener listener = Mockito.mock(BlockListener.class);
        builder.register(listener);
        when(listener.match(any(Block.class))).thenReturn(false);

        builder.parse("");

        verify(listener, never()).start(any(Block.class), any(SpannableStringBuilder.class));

    }

    @Test public void testCallsEndOnMatchingListener() throws Exception {
        FlavoredTextBuilder builder = new FlavoredTextBuilder(new MockParser());

        final BlockListener listener = Mockito.mock(BlockListener.class);
        builder.register(listener);
        when(listener.match(any(MockBlock.class))).thenReturn(true);

        builder.parse("");

        verify(listener).end(isA(MockBlock.class), isA(SpannableStringBuilder.class));
    }

    @Test public void testDoesNotCallEndOnNotMatchingListener() throws Exception {
        FlavoredTextBuilder builder = new FlavoredTextBuilder(new MockParser());

        final BlockListener listener = Mockito.mock(BlockListener.class);
        builder.register(listener);
        when(listener.match(any(Block.class))).thenReturn(false);

        builder.parse("");

        verify(listener, never()).end(any(Block.class), any(SpannableStringBuilder.class));
    }

    private static class MockParser implements Parser {

        private ParseListener listener;

        @Override public void parse(String string) {
            listener.start(new MockBlock());
            listener.characters("abc");
            listener.end(new MockBlock());
        }

        @Override public void register(ParseListener listener) {
            this.listener = listener;
        }
    }

    private static class MockBlock implements Block {}
}
