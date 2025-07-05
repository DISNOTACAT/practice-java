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


# 실무 예외 처리 방안
### 처리할 수 없는 예외
상대 네트워크의 문제로 문제가 발생했을 경우 예외를 잡는다고 해결이 되는 것일까? 이럴 경우 고객에게 "현재 시스템에 문제가 있습니다"라는 오류 메시지 또는 페이지를 보여주고 내부 개발자가 문제 상황을 인지할 수 있도록 로그를 남겨두어야 한다.

### 체크 예외의 부담
체크 예외는 많이 사용되었다. 그런데 앞서 설명한 것처럼 처리할 수 없는 예외가 많아지고, 프로그램이 복잡해지며 체크 예외를 사용하느 것이 부담스러워졌다.
실무에서는 수 많은 외부 시스템과 연동된다. 이 경우 서비스는 모든 예외를 잡아 처리해야한다. 하지만 처리할 수 없는 예외들이 있기 때문에 던지는 것이 더 나은 결정 일 수 있다.
이것을 던질 경우, throws에 명시해야할 예외가 매우 많아지게 된다. 결국 계속 밖으로 던지는 지저분한 코드가 생기게 되고, 최종 적으로 throws Exception 한 줄로 줄일 수 있겠지만
이것은 치명적인 문제가 있다.

모든 체크 예외를 다 밖으로 던지는 문제가 발생한다. 중요한 체크 예외가 있더라도 놓치게 된다. 따라서 꼭 필요한 경우가 아니라면 Exception 자체를 밖으로 던지는 것은 좋지 않은 방법이다.

### 언체크 예외 사용 시나리오
언체크 예외는 throws 선언 없이도 자동으로 밖으로 던진다. 앞서 이야기한 처리할 수 없는 예외의 경우 던지는 것이 나은 방법이다. 만약 일부 언체크 예외를 잡아서 처리할 수 있다면 잡아서 처리하면 된다.

### 예외 공통 처리
이렇게 처리할 수 없는 예외는 공통으로 처리할 수 있는 곳을 만들어 처리하면 된다. 시스템 문제가 있다는 메시지 또는 페이지를 보여주고 오류에 대한 로그를 남겨두면 된다.

### 구현

- 언체크 예외를 상속 받는다.
```java
public class NetworkClientExceptionV4 extends RuntimeException {

  public NetworkClientExceptionV4(String message) {
    super(message);
  }
}
```
- 서비스는 어차피 처리할 수 없는 예외는 던져버리고, 본연의 로직에 집중하여 간결한 코드를 작성할 수 있다.
```java
public class NetworkServiceV4 {
  public void sendMessage(String data) {
    String address = "http://example.com";
    NetworkClientV4 client = new NetworkClientV4(address);
    client.initError(data);

    try{
      client.connect();
      client.send(data);
    } finally {
      client.disconnect();
    }
  }
}
```

- main에서는 공통 예외 로직을 추가하여 처리한다.
```java
public class MainV4 {

  public static void main(String[] args) {
    NetworkServiceV4 service = new NetworkServiceV4();

    Scanner scanner = new Scanner(System.in);
    while(true) {
      System.out.print("전송할 문자: ");
      String input = scanner.nextLine();
      if (input.equals("exit")) {
        break;
      }

      try{
        service.sendMessage(input);
      } catch (Exception e) {
        exceptionHandler(e);
      }

      System.out.println();
    }
    System.out.println("프로그램을 정상 종료합니다.");
  }

  // 공통 예외 처리 로직
  private static void exceptionHandler(Exception e) {
    System.out.println("사용자 메시지: 죄송합니다. 알 수 없는 문제가 발생했습니다.");
    System.out.println("===log: 개발자용 디버깅 메시지===");
    e.printStackTrace(System.out);
//    e.printStackTrace();

    // 필요시 예외 별로 별도의 로직 추가
    if (e instanceof SendExceptionV4 sendEx) {
      System.out.println("[전송 오류] 전송 데이터: " + sendEx.getSendData());
    }
  }
}
```
- e.printStackTrace()
  - 예외 메시지와 스택 트레이스를 출력할 수 있다.
  - 이 기능을 사용하면 예외가 발생한 지점을 역으로 추적할 수 있다.
  - (참고로 예제에서는 e.printStacktrace(System.out)을 사용하여 표준 출력으로 보냈다.)
  - e.printStackTrace()를 사용하면 System.err 표준 오류에 결과를 출력한다.
    - IDE에서는 출력 결과를 빨간색으로 보여준다.
    - 일반적으로 사용하는 방법
> 실무에서는 콘솔에 출력하지 않고, 로그 라이브러리를 사용하기 때문에 파일로 로그를 관리한다.
    

## try-with-resources
try에서 외부자원을 사용하고 끝나면 자원을 반납하는 패턴이 반복되며, try-with0resources라는 편의 기능을 자바 7에서 도입했다.
- AutoCloseable 인터페이스를 구현해야 한다.
- 인터페이스를 구현하면 Try with resources를 사용할 때 try가 끝나는 시점에 close()가 자동으로 호출된다.
```java
package section10.ex4;


import section10.ex4.exception.ConnectExceptionV4;
import section10.ex4.exception.SendExceptionV4;

public class NetworkClientV5 implements AutoCloseable {

  private final String address;
  public boolean connectError;
  public boolean sendError;

  public NetworkClientV5(String address) {
    this.address = address;
  }

  public void connect() throws ConnectExceptionV4 {...}
  public void send(String data) throws SendExceptionV4 {...}
  
  public void disconnect() {...}
  public void initError(String data) {...}

  @Override
  public void close() {
    System.out.println("NetworkClientV5.class");
    disconnect();
  }
}
```
- close() : try 가 끝나면 자동으로 호출된다. 종료 시점에 자원을 반납하는 방법을 여기에 저으이하면 된다.
- Try with resources 구문은 try 괄호 안에 사용할 자원을 명시한다.
```java
package section10.ex4;

public class NetworkServiceV5 {

  public void sendMessage(String data) {
    String address = "http://example.com";

    try(NetworkClientV5 client = new NetworkClientV5(address)){
      client.initError(data);
      client.connect();
      client.send(data);
    } catch (Exception e) {
      System.out.println("예외 확인 : " + e.getMessage());
      throw e;
    }
  }
}
```
- 이 자원은 try 구문이 끝나면 자동으로 AutoCloseable.close()를 호출해서 자원을 해제한다.
- catch 블럭 없이 try만 있어도 close()는 호출된다.

### try-with-resources의 장점
- 리소스 누수 방지
- 코드 간결성 및 가독성 향상
- 스코프 범위 한정 (코드 유지보수가 쉬워진다.)
- 조금 더 빠른 자원 해제 (try 직후로, finally보다 빠른 위치에서 동작한다.)

---
