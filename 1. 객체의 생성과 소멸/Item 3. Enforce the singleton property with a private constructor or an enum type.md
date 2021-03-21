### Item 3: private constructor나 enum type으로 singleton property를 강제해라.

*Singleton*은 간단하게 클래스의 *Instantiated*(인스턴스화)가 정확히 한번만 진행된다.

*Single-tons*는 일반적으로 상태가 없는 객체나 함수 혹은 본질적으로 특별한 *system component* 들을 말한다.

**클래스를 싱글톤으로 만드는 것은 클라이언트의 테스트를 더 어렵게 한다.** 

왜냐하면, 타입으로 사용되는 인터페이스를 구현하지 않는 한, singleton을 *mock implementation*(모의구현)으로 대신하는 것은 불가능하기 때문이다.

여기에 싱글톤을 구현하는 두 가지 일반적인 방법이 있다.

**Example Code #1**

```java
// Singleton with public final field
public class Road {
  public static final Road INSTANCE = new Road();
  private Road() {}
  
  public void go() { ... }
}
```

이 방식으로 얻을 수 있는 장점은 **API**를 만들 때 그 클래스가 *singleton*임을 명백히 알 수 있다. 또한 더 단순하다.

**Example Code #2**

```java
// Singleton with static factory
public class Road {
   private static final Road INSTANCE = new Road();
   private Road() {}
   public static Road getInstance() { return INSTANCE; }
  
 	 public void go() { ... } 
}
```

이러한 *static factory method*를 통하여 얻을 수 있는 이점은 API를 바꾸지 않고도 싱글톤을 적용할 수 있다는 것이다.

또한, *generic singleton factory*를 사용할 수 있다.

마지막으로, *static factory*는 *method reference*가 *supplier*로써 사용할 수 있다는 것이다.

하지만, 단지 ``Serializable`` 을 상속하는 것으로는 충분하지 않다. 

모든 *instance*의 *field*를 *transident*로 정의하고 ```readResolve``` 를 제공하지 않으면, *serialized*된 *instance*가 *deserialized* 될 때, 새 *instance*가 생성될 것이다.

이러한 일이 일어나는 것을 막기 위해서는, ```readResolve``` method를 정의하여야 한다.

```java
// readResolve method가 singleton property를 보존한다.
private Object readResolve() {
	return INSTANCE;
}
```



Singleton을 구현하는 세번째 방법은 *single-element enum*을 활용하는 것이다.

```java
public enum Road {
  INSTANCE;
  
  public void go() { ... }
}
```

위 방법은 *public field approach*와 유사하지만, 이것은 더 간결하다.

*serialization machinery*를 자유롭게 제공하고, *multiple instantiation*에서 안전하다.

이 접근 방식은 약간 부자연스러운 느낌을 줄 수 있지만, 이것은 **singleton을 구현하는데에 있어서 최고의 방법이다.**

만약에 당신의 *singleton*이 ```Enum```이 아닌 다른 슈퍼클래스로 확장해야 하는 경우 이 방법을 사용할 수 없다는 것을 기억해라.