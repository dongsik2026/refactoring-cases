package abstract_till_you_drop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class SymbolReplacer {
    protected String stringToReplace;
    protected List alreadyReplaced = new ArrayList();
    private Pattern symbolPattern = Pattern.compile("\\$([a-zA-Z]\\w*)");
    private Matcher symbolMatcher;

    SymbolReplacer(String s) {
        this.stringToReplace = s;
        this.symbolMatcher = symbolPattern.matcher(stringToReplace);
    }

    String replace() {
        while (symbolMatcher.find()) {
            String symbolName = symbolMatcher.group(1);
            replaceAllInstances(symbolName);
        }
        return stringToReplace;
    }

    private void replaceAllInstances(String symbolName) {
        if (shouldReplaceSymbol(symbolName)) {
            alreadyReplaced.add(symbolName);
            stringToReplace = stringToReplace.replace("$" + symbolName, getSymbol(symbolName));
        }
    }

    private boolean shouldReplaceSymbol(String symbolName) {
        return getSymbol(symbolName) != null && !alreadyReplaced.contains(symbolName);
    }

    abstract protected String getSymbol(String symbolName);
}
