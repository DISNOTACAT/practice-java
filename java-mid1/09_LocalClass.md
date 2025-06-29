# 지역 클래스
- 지역클래스는 내부 클래스의 특별한 종류 중 하나로 내부 클래스의 특징을 그대로 가진다.
- 바깥 클래스의 인스턴스 멤버에 접근할 수 있다.
- 지역 변수 처럼 코드 블럭 안에서 선언한다.
- 지역 변수에 접근할 수 있다.
- 지역 클래스는 접근 제어자를 사용할 수 없다.

> 지역 클래스가 접근하는 지역 변수의 값은 변경하면 안된다. 왜?

## 지역 클래스 - 지역 변수 캡처
### 변수의 생명 주기
- 클래스 변수 : 메서드 영역 - 프로그램 종료까지 생존, 가장 길다.
- 인스턴스 변수 : 힙 영역 - 인스턴스의 생존 기간
- 지역 변수 : 스택 영역 - 메서드 호출이 끝나면 사라짐

```java
public class OuterClass {

  private int outInstanceVar = 3;

  public Printer process(int paramVar) {

    int localVar = 1; //기초 지식 : 지역 변수는 스택 프레임이 종료되는 순간 함께 제거된다.

    class LocalPrinter implements Printer { //기초 지식: 인스스턴스는 지역 변수 보다 오래 살아남는다.

      int val = 0;

      @Override
      public void print() {
        System.out.println(paramVar); // 매개 변수 출력
        System.out.println(val); // 내부 클래스의 지역 변수 출력
      }

    }
    LocalPrinter localPrinter = new LocalPrinter();
    return localPrinter;
  }
}
```
```java
public static void main(String[] args) {
        LocalOuterV3 localOuter = new LocalOuterV3();
        Printer printer = localOuter.process(2);
        //printer.print()를 나중에 실행한다. process()의 스택 프레임이 사라진 이후에 실행
        printer.print();
    }
```
> 바깥 클래스 내의 지역변수를 생성하는 process()메서드를 통해, Printer 인스턴스를 생성했다.
> 이때, 지역 클래스가 '지역 변수인 paramVar과 localVar'을 성공적으로 출력시키는 걸 볼 수 있다.
> 메서드가 종료 될 때, 스택 프레임이 종료되고 해당 지역 변수의 생명주기는 끝나게 된다.
> 하지만 Printer는 참조되는 곳이 있기 때문에 GC가 삭제하지 않고, 메모리에 남아있는다.
> 그런데 어떻게 다른 인스턴스에서 사라졌어야하는 지역 변수를 출력할 수 있을까?

지역 변수의 생명 주기는 짧고, 지역 클래스를 통해 생성한 인스턴스의 생명 주기는 길다.
지역 클래스를 통해 생성한 인스턴스가 지역 변수에 접근해야 하는데, 둘의 생명 주기가 다른 문제가 발생한다.

이런 문제를 해결하기 위해 지역 클래스 인스턴스를 생성하는 시점에 ***필요한 지역 변수를 복사해서 생성한 인스턴스와 함꼐 넣어둔다.*** 이런 과정을 **변수 캡처**라고 한다.
모든 지역 변수를 캡처하는 것이 아닌 접근이 필요한 지역 변수만 캡처한다.

### 지역 클래스의 인스턴스 캡처 과정
1. LocalPrinter 인스턴스 생성 시도 시점에 지역 클래스가 접근하는 지역 변수를 확인한다.
2. LocalPrinter 인스턴스 내부에, 필요한 지역 변수를 복사해서 저장한다. (paramVar, localVar)
3. LocalPrinter의 print() 메서드를 통해 paramVar, localVar 에 접근한건, 사실 인스턴스에 있는 캡처된 변수에 접근한 것이다.
- 캡처한 지역 변수의 생명 주기는 인스턴스의 생명주기와 같아진다.

### 참조값 확인하기
```java
Field[] fields = printer.getClass().getDeclaredFields();
```
LocalPrinter 인스턴스의 필드를 출력하면 아래와 같이 확인할 수 있다.
- 필드 확인  
  // 인스턴스 변수  
    field = int section09.LocalOuterV3$1LocalPrinter.value  
<br>
  // 캡처 변수  
    field = final int section09.LocalOuterV3$1LocalPrinter.val$localVar  
    field = final int section09.LocalOuterV3$1LocalPrinter.val$paramVar  
<br>
  // 바깥 클래스 참조  
    field = final section09.LocalOuterV3 section09.LocalOuterV3$1LocalPrinter.this$0

### 지역 클래스가 접근하는 지역 변수는 절대로 중간에 변하면 안된다.

> 지역 변수인 `int localVar = 1;`의 값을 변경하는 코드를 작성하면 컴파일 에러가 발생한다. paramVar도 마찬가지

**사실상 final - effectively final**
final로 선언하지 않았지만 선언뒤 한번도 변경되지 않음.

- 캡처한 이후에 변경하더라도 동기화 문제가 발생하여 컴파일 오류가 발생한다.
- 동기화 오류 : 스택 영역에 존재하는 지역 변수의 값과 인스턴스에 캡처한 캡처 변수의 값이 서로 달라짐
```java
Printer prpinter = new LocalPrinter();
// localVar = 10; // 컴파일오류
// paramVar = 10; // 컴파일오류
```
### 캡처 변수의 값을 변경하지 못하는 이유
- 지역 변수의 값을 변경하면 인스턴스에 캡처한 변수의 값도 변경해야 한다.
- 반대로 인스턴스에 있는 캡처 변수의 값을 변경하면 해당 원본 지역 변수의 값도 다시 변경해야 한다.
- 개발자 입장에서 보면, 예상하지 못한 곳에서 값이변경 될 수 있고 이는 디버깅을 어렵게 한다.
- 지역 변수의 값과 인스턴스에 있는 캡처 변수의 값을 서로 동기화 해야 하는데, 멀티 쓰레드 상황에서 이런 동기화는 매우 어렵고 성능 좋지 않다.

> 자바 언어를 설계할 때 열심히 수정하면 서로 변경 되게 할 수 있을 것이다. 하지만 아예 변경 할 수 없게 하므로서 복잡한 문제들을 근본적으로 차단한다.

> 정리: 지역 클래스가 접근하는 지역 변수의 값은 변경하면 안된다.

# 익명 클래스
익명 클래스는 클래스의 본문(body)를 정의하면서 동시에 new 생성한다.

```java
public class OuterClass {

  private int outInstanceVar = 3;

  public void process(int paramVar) {

    int localVar = 1; //기초 지식 : 지역 변수는 스택 프레임이 종료되는 순간 함께 제거된다.

    Printer printer = new Printer() { //기초 지식: 인스스턴스는 지역 변수 보다 오래 살아남는다.

      int val = 0;

      @Override
      public void print() {
        System.out.println(paramVar); // 매개 변수 출력
        System.out.println(val); // 내부 클래스의 지역 변수 출력
      }

    };
    printer.print();
  }
}
```
> 자바에서 인터페이스를 생성하는 것을 불가능하다. 이 코드는 인터페이스를 생성하는 것이 아니고, Printer라는 이름의 인터페이스를 구현한 익명 클래스를 생성하는 것이다.

### 익명 클래스 특징
- 이름 없는 지역 클래스를 선언하면서 동시에 생성한다.
- 익명 클래스는 **부모 클래스를 상속 받거나, 또는 인터페이스를 구현해야한다.**
- 이름이 없기 때문에 생성자를 가질 수 없다. (기본 생성자만 사용됨)
- 익명 클래스는 `OuterClass$1`과 같이 자바 내부에서 바깥 클래스 `이름 + $ + n`으로 정의된다.

### 익명 클래스 장점
클래스를 별도로 정의하지 않고도 인터페이스나 추상 클래스를 즉석에서 구현할 수 있어 코드가 간결해진다.
하지만, 복잡하거나 재사용이 필요한 경우에는 별도의 클래스를 정의하는 것이 좋다.

### 익명 클래스를 사용할 수 없을 때
익명 클래스는 단 한 번만 인스턴스를 생성할 수 있다. 여러번 생성이 필요하다면 익명 클래스를 사용할 수 없다. 대신에 지역 클래스를 선언하고 사용하면 된다.
- 불가능한 예제 코드
    ```java
    Printer print1 = new LocalPrinter();
    Printer print2 = new LocalPrinter();
    ```