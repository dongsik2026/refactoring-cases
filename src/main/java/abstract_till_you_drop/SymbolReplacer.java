package abstract_till_you_drop;

import java.util.ArrayList;
import java.util.List;

class SymbolReplacer {
    protected String stringToReplace;
    protected List alreadyReplaced = new ArrayList();
    private SymbolTranslator symbolTranslator;
    private SymbolIterator symbolIterator;

    SymbolReplacer(String s, SymbolTranslator symbolTranslator) {
        this.stringToReplace = s;
        SymbolIterator.symbolMatcher = SymbolIterator.symbolPattern.matcher(stringToReplace);
        this.symbolTranslator = symbolTranslator;
    }

    String replace() {
        for (String symbolName = nextSymbol();
             symbolName != null;
             symbolName = nextSymbol()) {
            replaceAllInstances(symbolName);
        }
        return stringToReplace;
    }

    private String nextSymbol() {
        return SymbolIterator.symbolMatcher.find() ? SymbolIterator.symbolMatcher.group(1) : null;
    }

    private void replaceAllInstances(String symbolName) {
        if (shouldReplaceSymbol(symbolName))
            replaceSymbol(symbolName);
    }

    private void replaceSymbol(String symbolName) {
        alreadyReplaced.add(symbolName);
        stringToReplace = stringToReplace.replace(
                "$" + symbolName,
                symbolTranslator.getSymbol(symbolName)
        );
    }

    private boolean shouldReplaceSymbol(String symbolName) {
        return symbolTranslator.getSymbol(symbolName) != null &&
                !alreadyReplaced.contains(symbolName);
    }

}
