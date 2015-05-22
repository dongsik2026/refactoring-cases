package abstract_till_you_drop;

public abstract class SymbolTranslator {
    abstract protected String getSymbol(String symbolName);

    String symbolExpression(String symbolName) {
        return "$" + symbolName;
    }
}
