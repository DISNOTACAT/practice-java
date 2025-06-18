# String

---

## String 문자 생성 방법
- 쌍따옴표 사용 : "hello"
- 객체 생성: new String("hello");

> 문자열은 매우 자주 사용된다. String은 클래스지만, 편의상 쌍따옴표로 작성하면 선언문으로 변경해준다.

## String 클래스 구조
```java
private final char[] value; // 자바 9 이전
private final byte[] value; // 자바 9 이후
```

> **자바 9 이후 `String` 클래스 변경 사항**  
> `char = 2byte`, 그외 `영어, 숫자는 1byte`가 사용됨. >>  메모리 효율 상승

### 메서드
- `lenght()`
- `charAt(int index)`
- `substring(int beginIndex, int endIndex)`
- `indexOf(String str)`
- `toLowerCase(), toUpperCase()`
- `trim()`
- `concat(String str)`

### String 클래스와 참조형
참조형은 변수에 계산할 수 있는 값이 들어있는 것이 아니라 `+`연산이 불가능하다.
하지만 자바에서는 문자열 연산을 자주하기 때문에, 편의상 `String + String`을 제공한다.


## String 비교
```java
public static void main(String[] args) {

    String strObj1 = new String("hello");
    String strObj2 = new String("hello");

    System.out.println("== 비교 : " + (strObj1 == strObj2)); // false
    System.out.println("equals() 비교 : " + strObj1.equals(strObj2)); // true

    String str1 = "hello";
    String str2 = "hello";

    System.out.println("== 비교 : " + (str1 == str2)); // true
    System.out.println("equals() 비교 : " + str1.equals(str2)); // true


  }
```

### 문자열 풀
`Obj`의 경우 인스턴스를 생성하였기 때문에, 참조값 비교가 필요하다.
`""` 과 같이 문자열 리터럴을 사용하는 경우, 메모리 효율성과 성능 최적화를 위해 문자열 풀을 사용 한다.  
자바 실행 시점에 클래스에 문자열 리터럴이 있으면 풀에 String 인스턴스를 미리 만들어둔다. 이때 같은 문자열이 있으면 만들지 않는다. (하나만)  
`str1`과 `str2` 는 모두 `"hello"` 문자열 리터럴을 사용하므로 동일한 인스턴스를 참조하게 된다.

따라서 문자열 리터럴을 사용하는 경우 같은 참조값을 사용하므로 `==`비교에 성공한다.

> 풀(Pool)은 고용 자원을 모아둔 곳을 뜻한다. 이를 통해 성능과 메모리를 최적화할 수 있다.  
> 문자열 풀은 힙 영역을 사용한다. 문자를 찾을 때는 해시 알고리즘을 사용하기 때문에 매우 빠른 속도로 찾을 수 있다.


> ***하지만 항상 equals 비교를 해야 하는 이유***  
> ```java
>  private static boolean isSameString(String str1, String str2) {
>    return str1 == str2;
>  }
>```
>
>다른 개발자가 위와 같이 비교문을 만들었다면, new String("hello")가 들어올지, "hello" 문자열 리터럴이 들어올지 알 수 있을까?


## String 불변객체
`String`은 불변이고 생성이후 절대로 변경할 수 없다.
> `concat` 은 `return new String()` 한다.

### 왜 String은 불변으로 설계되었나?
문자열 풀에 있는 String 인스턴스의 값이 중간에 변경되면 같은 문자열을 참고하는 다른 변수값도 변경된는 문제가 발생한다.


## String 주요 메서드
> `CharSequence`는 `String`, `StringBuilder`의 상위 타입이다.


## StringBuilder - 가변 String
계속 새로 문자를 조합하고 생성한다면, 사용하지 않는 인스턴스를 소모하게 된다.
실제로는 자바가 내부에서 최적화를 하지만, 성능과 메모리면에서 효율적인 `StringBuilder`를 제공한다.

> `StringBuilder`는 보통 문자열을 변경하는 동안만 사용하다가 문자열 변경이 끝나면 불변 `String`으로 변경하는 것이 좋다.

## 자바의 String 최적화
자바가 최적화를 처리해주기 때문에 간단한 경우에는 `+` 연산을 해도 충분하다.

### 문자열 리터럴 최적화
- 컴파일 전
    ```java
    String helloWorld = "Hello, " + "World!"; // 컴파일 전
    ```
- 컴파일 후
    ```java
    String helloWorld = "Hello, World!"; // 컴파일 후
    ```

런타임에 별도의 문자열 결합 연산을 수행하지 않는다.

### String 변수 최적화
- String 변수의 예제
    ```java
    String result = str1 + str2;
    ```
- 최적화 예시 (자바 버전마다 다름)
    ```java
    String result = new StringBuilder().append(str1).append(str2).toString();
    ```
> 자바9 부터는 `StringConcatFactory()`를 사용해서 최적화를 수행한다.

### StringBuilder를 직접 사용하는 것이 좋은 경우
문자열을 루프안에서 문자열 더하는 경우 최적화가 이루어지지 않는다.  
반복문 내에서 문자열 연결은 런타임 시점에 반복이 결정된다.  
따라서 이런 상황에서는 최적화가 어렵다.  

이럴때, 직접 `StringBuilder`를 사용해주면 된다.

- 반복문에서 반복해서 문자를 연결할 때
- 조건문을 통해 동적으로 문자열을 조합할 때
- 복잡한 문자열의 특정 부분을 변경해야 할 때
- 매우 긴 대용량 문자열을 다룰 때

### StringBuffer
StringBuffer는 내부에 동기화 되어있어 멀티 쓰레드 상황에서 안전하지만 동기화 오버헤드로 인해 성능이 느리다.  
StringBuilder는 멀티 쓰레드 환경에서 안전하지 않지만, 동기화 오버헤드가 없으므로 속도가 빠르다.


## 메서드 체이닝
메서드 호출의 결과로 자기 자신의 참조값(this)을 반환하면, 반환된 참조값을 사용해서 호출을 이어갈 수 있다. 이런 것을 메서드 체이닝이라고 한다.
메서드 체이닝 방식은 메서드가 끝나는 시점에 바로 .을 찍어서 변수명을 생략할 수 있다.

```java
package section04;

public class Chain {

  private static class ValueAdder {

    private int value;

    public ValueAdder add(int addValue) {
      value += addValue;
      return this;
    }

    public int getValue() {
      return value;
    }
  }

  public static void main(String[] args) {

    ValueAdder adder = new ValueAdder();
    ValueAdder result = adder.add(1).add(2).add(3);
    System.out.println(result.getValue()); // result = 6
  }
}

```

> 메서드 체이닝 기법은 코드를 간결하고 읽기 쉽게 만들어 준다. 

### StringBuilder 와 Chain
StringBuilder에서 문자열을 변경하는 대부분의 메서드도 메서드 체이닝 기법을 제공하기 위해 자기 자신을 반환한다.

```java
  StringBuilder sb = new StringBuilder();
  String str = sb.append("A").append("B").append("C")
      .insert(4, "Java")
      .delete(4, 8)
      .reverse()
      .toString();
```

> 메서드 체이닝은 구현하는 입장에서는 번거롭지만, 사용하는 개발자는 편리해진다.