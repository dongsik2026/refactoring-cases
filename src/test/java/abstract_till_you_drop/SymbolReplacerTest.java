package abstract_till_you_drop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SymbolReplacerTest {
    @Test
    public void foo() {
        SymbolReplacer replacer = new SymbolReplacer("$ss aa $bb dd ff ss", new MyReplacer());
        assertThat(replacer.replace(), is("__ aa __ dd ff ss"));
    }
}