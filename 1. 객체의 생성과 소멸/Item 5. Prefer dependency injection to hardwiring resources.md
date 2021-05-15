## Item 5. 자원 연결에 의존성 역전을 취해라.

많은 *class*들은 많은 리소스들에 의존한다. 예를 들어 맞춤법 검사기는 사전에 의존한다.
그러한 것들은 여러 *static utility class*들에서 흔치 않게 볼 수 있다.

```java
// 적절하지 않은 static utility의 사용 - 테스트가 불가능하고, 불변하다
public class SpellChecker {
    private static final Lexicon dictionary = ...;
    
    private SpellChecker() {}
    
    public static boolean isValid(String word) { ... }
    public static List<String> suggestions(String typo) { ... }
}
```
