# java.lang 패키지

### 대표 클래스
- Object
- String
- Integer, Long, Double
- Class
- System

> java.lang 은 import 생략 가능하다.

# Object 클래스
모든 클래스의 최상위 부모 클래스는 Object 클래스이다.
> 부모가 없으면 묵시적으로 Object 클래스를 상속 받는다. ```extends Object```

```java
public static void main(String[] args) {
  
  Child child = new Child();
  // toString()은 Object 클래스의 메서드
  String str = child.toString();
}
```

### 자바에서 Object 클래스가 최상위 부모인 이유
- 공통 기능 제공
- 다형성의 기본 구현


## Object 다형성
- 장점
    - `Object`는 모든 객체의 부모로 어떤 객체든지 인자로 전달할 수 있다.
- 한계
  - `Object`를 통해 받은 객체를 호출하려면 다운캐스팅 과정이 필요하다.


## Object 배열
```java
Object[] objects = {dog, cat, cow};
```
모든 객체를 담을 수 있는 클래스 역할을 할 수 있다.


## toString()
```java
  public static void main(String[] args) {

    Object obj = new Object();
    String str = obj.toString();

    System.out.println(obj); // java.lang.Object@6acbcfc0
    System.out.println(str); // java.lang.Object@6acbcfc0

  }
```
`println()` 또한 내부에서 `toString()`을 호출하고 있기 때문에, 동일한 방식으로 출력한다.

> **Object.toString()**
> ```java
> public String toString() {
>   return getClass().getName() + "@" + Integer.toHexString(hashCode());
> } 
> ```

### toString() 오버라이딩

```java
    @Override
    public String toString() {
      return "Dog{" + "name='" + name + '\'' + '}';
    }
```

`Object`의 메서드 오버라이딩을 통해서, 어디서나 같은 방식으로 출력할 수 있다. 

> 오버라이딩 이후 참조값을 알고 싶다면,  
> ```Integer.toHexString(Ststem.identityHshCode(dog)```

## Object와 OCP
위와 같은 방식은 **구체적인 것에 의존한다.** 새로운 객체 타입이 생성된다면 모두 위와 같이 작성해주어야한다.
하지만, `ObjectPrinter` 클래스에 `Object.toString()`을 구현한다면 **추상적인 것에 의존한다.**  
이러한 구조는, 
- 다형성을 매주 잘 활용하는 예시가 된다.
- Object 객체에 의존하기 때문에 하위 클래스가 변경되어도 코드가 바뀌지 않는다.


### System.out.println()
결과적으로 `System.out.println()` 메서드도 `Object`를 매개변수로 동작하는 것을 설명한다.

자바 언어는 객체지향 언어답게, 기본으로 제공되는 다양한 메서드들은 개발자가 오버라이딩해서 사용할 수 있도록 설계되어있다.

> 정적 의존 vs 동적 의존
> - 정적 의존 관계 : 컴파일시에 결정됨. 주로 클래스 간의 관계 (대부분의 의존성 언급은 여기 포함됨)
> - 동적 의존 관계 : 런타인시에 결정됨. 매개변수로 어떤 인자가 넘어올지는 실행될 때 알 수 있다.


# equals()
- 동일성(Identity) : `==`
  - 동일한 객체 참조값을 가리키는 지 확인 (물리적 메모리 구조 기반)
- 동등성(Equality) : `equals()`
  - 두 객체가 논리적으로 동등한지 (사람이 인지 하는 것)

```java
  public boolean equals(Object obj) {
        return (this == obj);
    }
```
> 동등성이라는 개념은 클래스 마다 다르다.  
> 예) 주민번호 기반, 고객 연락처 기반, 회원 번호 기반 등등  
> 따라서 동등성 비교를 하고 싶다면 `equals()` 메서드를 재정의 해야 한다.

### 정확한 equals() 구현의 규칙
- 반사성(Reflexivity) : 객체는 자기 자신과 동등
- 대칭성(Symmetry)
- 추이성(Transitivity) : a = b, b = c --> a == c
- 일관성(Consistency)
- null 에 대한 비교

> 동일성 비교가 하고 싶을 떄만 equals()를 재정의하면 된다.(IDE의 도움으로)


# Object의 나머지 메서드
- `clone()`
- `hashCode()`
- `getClass()`
- `notify()`, `notifyAll()`, `wait()` -> 멀티쓰레드용 메서드