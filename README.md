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

### 2. 테스트 추가

#### abstract method에 대한 구현체를 추가.

```
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
```

#### characterization test를 추가한다.

```
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
```

#### make it pass
테스트를 실행시켜서 시스템이 원하는 값을 알아내고 이를 이용하여 테스트를 성공시킨다. <- **characterization test**
![](https://api.monosnap.com/rpc/file/download?id=cT5fuxvetaBk2Abf3HvafvJGdyJq6w)

### 3. Prepare Extract Methods
symbolPattern, symbolMatcher를 field로 추출
큰 메소드를 작은 메소드를 extract하기 전의 사전 작업. 이 작업을 수행하면 extract method할 때 parameter로 전달할 필요가 없어진다.

![](https://api.monosnap.com/rpc/file/download?id=7kt24mePKPJ9fu9FQ77CaccQO4hjoc)

### 4. Extract method: replaceAllInstances
![](https://api.monosnap.com/rpc/file/download?id=V1h4JkqF7NA0qDFjHe8ZnlCakPqkxc)

### 5. Extract method: shouldReplaceSymbol
![](https://api.monosnap.com/rpc/file/download?id=eWaR2s1CW2TtEFTUoAUw9Hig3mKP3l)