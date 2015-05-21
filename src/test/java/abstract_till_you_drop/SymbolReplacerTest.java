package abstract_till_you_drop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SymbolReplacerTest {
    @Test
    public void foo() {
        MyReplacer replacer = new MyReplacer("$ss aa $bb dd ff ss");
        assertThat(replacer.replace(), is("xx"));
    }
}