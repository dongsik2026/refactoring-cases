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
        this.symbolTranslator = symbolTranslator;
        this.symbolIterator = new SymbolIterator(s);
    }

    String replace() {
        for (String symbolName = symbolIterator.nextSymbol();
             symbolName != null;
             symbolName = symbolIterator.nextSymbol()) {
            replaceAllInstances(symbolName);
        }
        return stringToReplace;
    }

    private void replaceAllInstances(String symbolName) {
        if (shouldReplaceSymbol(symbolName))
            replaceSymbol(symbolName);
    }

    private void replaceSymbol(String symbolName) {
        alreadyReplaced.add(symbolName);
        symbolTranslator.translate(symbolName, this);
    }

    private boolean shouldReplaceSymbol(String symbolName) {
        return symbolTranslator.getSymbol(symbolName) != null &&
                !alreadyReplaced.contains(symbolName);
    }
}
