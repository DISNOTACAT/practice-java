# 예외 처리가 필요한 이유
외부 서버와 통신할 때는 다음과 같은 다양한 문제들이 발생한다.
- 외부 서버와의 연결에 실패한다. (네트워크 오류 등등)
- 데이터 전송에 문제가 발생한다.

# 예외 계층
예외 키워드 : `try`, `catch`, `finally`, `throw`, `throws`

- `Object` : 자바의 기본형을 제외한 모든 것은 객체다. 예외의 최상위 부모
- `Throwable` : 최상위 예외. 하위에 `Exception`과 `Error`가 있다.
  - `Error` : 메모리 부족이나 심각한 시스템 오류와 같이 애플리케이션 복구가 불가능한 시스템이다. 애플리케이션 개발자는 이 예외를 잡으려고 해서는 안된다.
  - `Exception` : 체크 예외
    애플리 케이션 로직에서 사용할 수 있는 실질적인 최상위 예외이다. 
        `Exception`과 그 하위 예외는 모두 컴파일러가 체크하는 체크 예외이다. 단 `RuntimeException`은 예외로 한다.
    - `RuntimeException` : 언체크 예외, 런타임 예외
      - 컴파일러가 체크하지 않는 언체크 예외. `RuntimeException`과 그 자신 예외는 모두 언체크 예외이다.

> 상속 관계 개념은 예외 처리에도 적용되는데, 상위 예외를 catch로 잡으면 그 하위 예외까지 잡힌다. 따라서 Throwable 예외를 잡으면 Error 예외까지 함께 잡히기 때문에, 애플리케이션 로직은 Eception부터 필요한 예외로 생각하고 잡으면 된다.

## 예외 기본 규칙
예외는 폭탄 돌리기와 같다. 예외가 발생하면 잡아서 처리하거나, 처리할 수 없으면 밖으로 던져야 한다.
1. 예외는 잡아서 처리하거나 밖으로 던져야 한다.
2. 예외를 잡거나 던질 때 지정한 예외뿐만 아니라 그 예외의 자식들도 함께 처리할 수 있다.
   - 예) Exception을 catch로 잡으면 그 하위 예외들도 모두 잡을 수 있다.
   - 예) Exception을 throws로 던지면 그 하위 예외들도 모두 던질 수 있다.
> 예외를 처리하지 못하고 자바 main() 밖으로 예외를 던지면 예외 로그를 출력하면서 시스템이 종료된다.

### CheckedException
```java
public class MyCheckedException extends Exception {
  public MyCheckedException(String message) {
    super(message);
  }
}

public class Client {
  public void call() throws MyCheckedException {
    // 문제 발생
    throw new MyCheckedException("ex");
  }
}
```
- `throw` : 새로운 예외를 발생시킬 수 있다. 예외도 객체이기 때문에 객체를 먼저 `new`로 생성하고 예외를 발생시켜야 한다.
- `throws` : 발생시킨 예외를 메서드 밖으로 던질 때 사용하는 키워드이다.


### 예외를 잡거나 던지거나
```java
public class Service {

  Client client = new Client();
  /*
    예외를 잡아서 처리하는 코드
   */
  public void callCatch() {
    try {
      client.call();
    } catch (MyCheckedException e) { // catch의 대상에 없으면 예외를 잡지 못하고 밖으로 던져야한다.
      // 예외 처리 로직
      System.out.println("예외 처리, message = " + e.getMessage());
    }
    System.out.println("정상 프로세스 흐름");
  }

  /*
    체크 예외를 밖으로 던지는 코드
    체크 예외는 예외를 잡지 않고 밖으로 던지려면 throws 예외를 메서드에 필수로 선언해야 한다.
   */
  public void catchThrow() throws MyCheckedException{
    client.call();
  }
}
```


### 예외를 밖으로 던지기
```java
public class CheckedThrowMain {

  public static void main(String[] args) throws MyCheckedException {
    Client client = new Client();
    client.call();
    System.out.println("정상 종료");
  }
}
```
예외가 main() 밖으로 던져지면 예외 정보와 스택 트레이스(Stack Trace)를 출력하고 프로그램이 종료된다.
- 스택 트레이스 정보를 활용하면 예외가 어디서 발생했는지, 그리고 어떤 경로를 거쳐서 넘어왔는지 확인할 수 있다.

Throws를 지정하지 않으면 컴파일 오류가 발생한다.  
체크 예외는 직접 처리하거나 또는 밖으로 던지거나 둘 중 하나를 개발자가 직접 명시적으로 처리해야한다.

> 참고로 체크 예외를 밖으로 던지는 경우에도 해당 타입 또는 상위 타입을 던질 수 있다.

###  체크 예외의 장단점
체크 예외는 예외를 잡아서 처리할 수 없을 때, 예외를 밖으로 던지는 throws 예외를 필수로 선언해야한다.
- 장점: 개발자가 실수로 예외를 누락하지 않도록 컴파일러를 통해 문제를 잡아주는 안전 장치이다. 이를 통해 개발자는 어떤 체크 예외가 발생하는지 쉽게 파악할 수 있다.
- 단점: 하지만 실제로는 개발자가 모든 체크 예외를 반드시 잡거나 던지도록 처리해야 하기 때문에, 번거로운 일이 된다. 크게 신경 쓰고 싶지 않은 예외까지 모두 챙겨야 한다.

## 언체크 예외
컴파일러가 예외를 체크하지 않는다.  
기본적으로 체크 예외와 동일하지만, 언체크 예외는 throws를 선언하지 않고 생략할 수 있다. 생략한 경우 자동으로 예외를 던진다.

```java

public class Service {
  Client client = new Client();
  public void callCatch() {
    try {
      client.call();
    } catch (MyUncheckedException e) {
      System.out.println("예외 처리, message = " + e.getMessage());
    }
    System.out.println("정상 로직");
  }

  /*
  예외를 잡지 않아도 자동으로 처리된다.
   */
  public void callThrow() {
    client.call();
  }
}

```

> 체크 예외 vs 언체크 예외
> - 체크 예외 : 예외를 잡아서 처리하지 않으면 항상 throws 키워드를 사용해서 던지는 예외를 선언해야 한다.
> - 언체크 예외 : 예외를 잡아서 처리하지 않아도 throws키워드를 생략할 수 있다.

> 참고로 언체크 예외도 throws 예외를 선언해도 된다. (생략가능하지만)
> ```java
> public class Client {
>  public void call() throws MyUncheckedException {
>    throw new MyUncheckedException("ex");
>  }
> }
> ```
> 언체크 예외는 주로 생략하지만, 중요한 예외의 경우 선언해두면 해당 코드를 호출하는 개발자가 이런 예외가 발생한다는 점을 IDE를 통해 좀 더 편리하게 인지할 수 있다.


### 언체크 예외의 장단점
- 장점: 신경쓰고 싶지 않은 언체크 예외를 무시할 수 있다. 체크 예외의 경우 처리할 수 없는 예외를 밖으로 던지려면 항상 throws 예외를 선언해야하지만, 언체크 예외는 이 부분을 생략할 수 있다.
- 단점: 언체크 예외는 개발자가 실수로 예외를 누락할 수 있다. 반면에 체크 예외는 컴파일러를 통해 예외 누락을 잡아준다.

> 현대에는 체크 예외를 잘 쓰지 않는다. 왜냐?.. 일단 계속 수업을 진행한다.

### finally
자바는 어떤 경우라도 반드시 호출되는 finally 기능을 제공한다.
```java
try {
    // 정상흐름
} catch {
    // 예외 흐름
} finally {
    // 반드시 호출해야 하는 마무리 흐름
}
```
> `catch`에서 잡지 못하는 예외가 발생하더라도, `finally` 로직을 수행하고 예외를 밖으로 던지게 된다.

> `catch` 없이 `try~finally`만 사용할 수도 있다. 예외를 직접 잡을 필요없을 때 사용

## 예외 계층
예외를 계층화하면 다음과 같은 장점이 있다.
- 자바에서 예외는 객체이다. 따라서 부모 예외를 잡거나 던지면, 자식 예외도 함께 처리할 수 있다.
- 특정 예외를 잡아서 처리하고 싶으면 하위 예외를 잡아서 처리하면 된다.
