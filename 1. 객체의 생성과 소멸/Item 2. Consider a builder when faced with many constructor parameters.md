### Item 2: 생성자의 매개변수가 많을 때, 빌더를 고려하라.

정적 팩토리와 생성자는 많은 수의 선택적인 매개변수들이 있을 때, 확장성이 좋지 않다.

전통적으로, 프로그래머들은 선택적인 매개변수들을 사용할 수 있는 생성자를 제공할 때, *telescoping constructor pattern*(점층적 생성자 패턴)을 사용하였다.

```java
// Telescoping constructor pattern - 확장성이 좋지 않다!
public class NutritionFacts {
  private final int servingSize;	// (mL)			required
  private final int servings;		// (per container)	required
  private final int calories;		// (per serving)	optional
  private final int fat;		// (g/serving)		optional
  private final int sodium;		// (mg/serving)		optional
  private final int carbohydrate;	// (g/serving)		optional
  
  public NutritionFacts(int servingSize, int servings) {
    this(servingSize, servings, 0)
  }
  
  public NutritionFacts(int servingSize, int servings, int calories) {
    this(servingSize, servings, calories, 0);
  }
  
  public NutritionFacts(int servingSize, int servings, int calories, int fat) {
    this(servingSize, servings, calories, fat, 0);
  }
  
  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
    this(servingSize, servings, calories, fat, sodium, 0);
  }
  
  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
    this.servingSize  = servingSize;
    this.servings     = servings;
    this.calories     = calories;
    this.fat	      = fat;
    this.sodium	      = sodium;
    this.carbohydrate = carbohydrate;
  }
}
```

만약 당신이 인스턴스를 만드는 것을 원한다면, 당신이 설정하고자 하는 매개변수를 모두 포함하는 생성자를 사용한다.

```java
NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
```

이 경우에는 fat에 0을 넣음으로써 default 값으로 설정하였다.

"오직" 6개의 매개변수들은 나쁘지 않게 보일 수 있지만, 매개변수들이 늘어날수록 감당할 수 없게 된다.

요약해서, *telescoping constructor pattern*은 작동하지만, 많은 매개변수들이 있을 때, client code를 작성하기에 어렵고, 그것을 읽기에는 더 어렵다.

-------------

#### 두번째 대안

당신이 많은 선택적인 매개변수를 다뤄야 할때의 두번째 대안으로는, *JavaBeans Pattern*(자바빈 패턴)이 있다.

당신이 *매개변수를 받지 않는 생성자(parameterless constructor)*를 호출하여 객체를 생성하고, *setter methods*를 호출하여 필요 매개변수와 선택적 매개변수를 설정한다.

```java
// JavaBean Pattern - allows inconsistency, mandates mutability(불일치성을 가지며, 가변성이 강제된다)
public class NutritionFacts {
  // 매개변수들은 기본값으로 초기화된다.
  private int servingsSize; 	// Required; no default value
  private int servings; 	// Required; no default value
  private int calories 	   = 0;
  private int fat          = 0;
  private int sodium       = 0;
  private int carbohydrate = 0;
  
  public NutritionFacts() { }
  
  // Setters
  public void setServingSize(int val)  { servingSize = val; }
  public void setServings(int val)     { servings = val; }
  public void setCalories(int val)     { calories = val; }
  public void setFat(int val)          { fat = val; }
  public void setSodium(int val)       { sodium = val; }
  public void setCarbohydrate(int val) { carbohydrate = val; }
}

```

이 패턴은 *telescoping constructor patterns*의 단점을 가지고 있지는 않다.

이 방법은 쉽고, 코드를 읽기에 수월하다.

```java
NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240);
cocaCola.setServings(8);
cocaCola.setCalories(100);
cocaCola.setSodium(35);
cocaCola.setCarbohydrate(27);
```

불행히도,  *JavaBean pattern*은 중요한 단점을 가지고 있다.

객체를 구성하는 과정에서 여러번의 호출로 분할 된다는 것인데, 이는 생성 과정에서 일관성이 없을 수 있다.

 또, *JavaBean pattern*은 불변 객체를 만들 수 없다.

이러한 단점들은 객체를 동결하는 것(객체가 불변성을 가지게 하는 것)을 수동적으로 해야한다.

더 나아가, 객체를 동결하는 것을 수동적으로 한다는 것은 *runtime*시 컴파일러가 그것을 사용하기 전 프로그래머가 freeze method를 객체에 호출하였는지 장담할 수 없기 때문에 더 많은 에러를 발생시킬 수 있다.

-------------------------

#### 빌더 패턴(Builder Pattern)

운 좋게도, *telescoping constructor pattern*의 안정성과 *JavaBeans pattern*의 가독성을 모두 가지고 있는 세번째 대안이 있다.

바로 Builder Pattern 형식이다.

원하는 객체를 직접 만드는 대신에, client는 생성자 혹은 static factory와 요구 매개변수들을 호출하여 *builder object*를 얻는다.

client는 builder object에 있는 *setter*와 닮은 메소드를 호출하여 선택적 매개변수를 설정한다.

마침내, client는 *parameterless*한 build 메소드를 호출하여 객체를 얻는다. 이것은 일반적으로 불변한다.

builder는 일반적으로 빌드하는 class의 static member class이다.

```java
// Builder Pattern
public class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;
  
  public static class Builder {
    // Required parameters
    private final int servingSize;
    private final int servings;
    
    // Optional parameters - initialized to default values
    private int calories     = 0;
    private int fat 	     = 0;
    private int sodium       = 0;
    private int carbohydrate = 0;
    
    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings    = servings;
    }
    
    public Builder calories(int val) {
      calories = val;
      return this;
    }
    
    public Builder fat(int val) {
      fat = val;
      return this;
    }
    
    public Builder sodium(int val) {
      sodium = val;
      return this;
    }
    
    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    } 
    
    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }
  
  private NutritionFacts(Builder builder) {
    servingsSize = builder.servingsSize;
    servings 	 = builder.servings;
    calories     = builder.calories;
    fat 	 = builder.fat;
    sodium       = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }
}
```

NutritionFacts는 불변하고, 모든 매개변수의 기본값이 한곳에 있다. builder의 *setter* 메소드는 자기 자신을 반환하여 *chain*이 가능하다.

```java
NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build()
```

이 *client code*는 가독성이 좋고 뭐가 더 중요한지를 수월하게 작성했다.

------------

#### Generic Builder

*Builder pattern*은 클래스 계층에 적합하다. *parallel hierarchy of Builder*(병렬 계층의 빌더)를 사용하여 각각 해당 클래스에 중첩한다.

```java
// Builder pattern for class hierarchies
public abstract class Pizza {
  public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
  final Set<Topping> toppings;
  
  abstract static class Builder<T extends Builder<T>> {
    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
    public T addTopping(Topping topping) {
      toppings.add(Objects.requireNonNull(topping));
      return self();
    }
    
    abstract Pizza build();
    
    // Subclasses는 이 메소드를 반드시 return "this"로 override해야한다.
    protected abstract T self();
  }
  
  Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone(); // See Item 50
  }
}
```

