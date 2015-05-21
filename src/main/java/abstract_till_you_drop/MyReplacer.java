package abstract_till_you_drop;

public class MyReplacer extends SymbolTranslator {
    @Override
    protected String getSymbol(String symbolName) {
        return "__";
    }
}
