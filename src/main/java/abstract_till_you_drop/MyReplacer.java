package abstract_till_you_drop;

public class MyReplacer extends SymbolReplacer {
    MyReplacer(String s) {
        super(s);
    }

    @Override
    protected String getSymbol(String symbolName) {
        return "__";
    }
}
