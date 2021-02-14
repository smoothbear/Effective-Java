### Item 1: 생성자 대신에 Static Factory Method 사용을 고려하라.

인스턴스를 제공하는 전통적인 방법으로 생성자를 사용한다.

여기에 인스턴스를 제공하는 또 다른 방법이 있다.

클래스는 public static factory method를 제공할 수 있다.

#### 예시

Boolean 클래스에서의 단순한 예시가 있다.

이 메소드는 boolean 원시값을 Boolean 객체로 바꾸어준다.

```java
public static Boolean valueOf(boolean b) {
  return b ? Boolean.TRUE : Boolean.FALSE;
}
```

#### Static Factory Method 사용으로 얻게 되는 이점

- 생성자와 다르게 static factory method는 이름을 가지고 있다.
  - 따라서, 반환될 객체가 어떠한 객체인지 쉽게 알 수 있다.
- static factory method는 호출할 때마다 항상 새로운 객체를 만드는 것을 요구하지 않는다.
  - 의도하지 않은 중복된 객체가 생기는 것을 피할 수 있다.
- 반환 타입의 하위 타입을 반환 할 수 있다.
  - 반환되는 객체의 클래스를 고르는 것에 훌륭한 적응성을 제공해준다.
- 입력 파라미터에 따라 매번 다른 객체를 반환할 수 있다.

- static factory method를 사용할 때 반환할 객체의 클래스는 존재하지 않아도 된다.

----

#### 한계

- public 생성자 혹은 protected 생성자가 없이 static factory method만 존재할 경우, 상속을 할 수 없다.

- static factory method는 프로그래머가 찾기에 어렵다. (확인작업 필요)

  - 대중적으로 사용되는 이름들

    - from : 형 변환 메소드, 하나의 매개변수를 가지며 인스턴스의 타입에 상응하는 객체를 반환한다.

      ```java
      Date d = Date.from(instant);
      ```

    - of : 집합 메소드, 여러개의 매개변수를 가지고 그것들을 통합한 인스턴스를 반환한다.

      ```java
      Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
      ```

    - valueOf : from과 of의 자세한 대안.

      ```java
      BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
      ```

    - instance | getInstance : 기술된 매개변수로 인스턴스를 반환한다. 하지만, 매개변수와 같은 값을 가진다고 말할 수 없다.

      ```java
      StackWalker luke = StackWalker.getInstance(options);
      ```

    - create | newInstance : instance | getInstance와 비슷하지만, 매개변수와 같은 값을 보장하는 새 인스턴스를 반환한다.

      ```java
      Object newArray = Array.newInstance(classObject, arrayLen);
      ```

    - getType : getInstance와 비슷하지만, factory method가 다른 class를 반환할때 사용된다.

      ```java
      BufferedReader br = Files.newBufferedReader(path);
      ```

    - newType : newInstance와 비슷하지만, factory method가 다른 class를 반환할때 사용한다.

      ```java
      BufferedReader br = Files.newBufferedReader(path);
      ```

    - type : getType과 newType의 간결한 대안이다.

      ```java
      List<Complaint> litany = Collections.list(legacyLitany);
      ```

      

-------------------------

