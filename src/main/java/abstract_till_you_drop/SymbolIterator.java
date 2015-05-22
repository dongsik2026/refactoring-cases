package abstract_till_you_drop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolIterator {
    static Pattern symbolPattern = Pattern.compile("\\$([a-zA-Z]\\w*)");
    static Matcher symbolMatcher;

    String nextSymbol() {
        return symbolMatcher.find() ? symbolMatcher.group(1) : null;
    }
}
