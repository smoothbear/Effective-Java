### Item 4. 객체 생성을 막을 때는 private constructor를 사용해라.

때때로 당신이 *static methods*나 *static fields*를 단순히 엮기 위한 용도로 클래스를 작성한다면, 이러한 클래스들은 이러한 것들을 객체 측면에서 피하는 몇몇 사람들에게 나쁜 평가를 받는다. 그러나 그것들은 유효하게 사용이 된다.

그것들은 관계있는 여러 메서드나 값을 집약할 때 사용한다.
이러한 *utility class*들은 객체로 만들기 위해 설계된 것이 아니다.
이러한 것들은 *private constructor*를 만들어 객체 생성을 제한하고 생성자 호출 시 **exception**을 던지도록 해야 한다.

만약, *private constructor*를 만들지 않는다면, *compiler*가 기본 생성자를 만들 수 있다.

```java
// 객체 생성이 되지 않는 클래스
public class UtilityClass {
  private UtilityClass() {
    throw new AssertionError();
  }
}
```
