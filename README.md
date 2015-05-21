# Refactoring Cases

## [abstract till you drop](https://thinkfoo.wordpress.com/2015/05/17/one-thing-abstract-till-you-drop/)

이 예제는 extract till you drop(extract method를 수행할 수 없을 때까지 수행)을 한 후에 남게되는 많은 작은 private method들을 역할에 맞게 다른 클래스로 위치시키는 것을 보여준다.

extract till you drop은 [github](https://raw.githubusercontent.com/msbaek/clean-coders-2013/master/2.Functions.png), [youtube](https://www.youtube.com/watch?v=GYNT7O3rLhU)에서 자세히 보실 수 있습니다.

### 1. 최초 클래스

```
package abstract_till_you_drop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class SymbolReplacer {
    protected String stringToReplace;
    protected List alreadyReplaced = new ArrayList();

    SymbolReplacer(String s) {
        this.stringToReplace = s;
    }

    String replace() {
        Pattern symbolPattern = Pattern.compile("\\$([a-zA-Z]\\w*)");
        Matcher symbolMatcher = symbolPattern.matcher(stringToReplace);
        while (symbolMatcher.find()) {
            String symbolName = symbolMatcher.group(1);
            if (getSymbol(symbolName) != null && !alreadyReplaced.contains(symbolName)) {
                alreadyReplaced.add(symbolName);
                stringToReplace = stringToReplace.replace("$" + symbolName, getSymbol(symbolName));
            }
        }
        return stringToReplace;
    }

    abstract protected String getSymbol(String symbolName);
}
```
