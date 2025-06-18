# 불변 객체

## 문제의 배경

---


## 기본형과 참조형의 공유
- 기본형 Primitive Type : 공유x
- 참조형 Reference Type : 공유o

```java
Address a = new Address("서울");
Address b = a; // a의 인스턴스 값이 복사됨

b.setValue("부산");
System.out.println(a) // 부산
System.out.println(b) // 부산
```

>자바에서 모든 값 대입은 변수가 가지고 있는 값을 복사해서 전달한다. 변수가 int 같은 숫자값을 가지고 있으면 숫자값을 복사해서 전달하고, 참조값을 가지고 있으면 참조값을 복사해서 전달한다.  
> 참조형 변수들은 같은 참조값을 통해 같은 인스턴스를 참조할 수 있다.
> 기본형 변수는 절대로 같은 값을 공유하지 않는다.


### 공유 참조와 사이드 이펙트 해결 방안
처음부터 다른 인스턴스값을 참조하면 된다.

```java
Address a = new Address("서울");
Address b = new Address("서울");

b.setValue("부산");
System.out.println(a) // 서울
System.out.println(b) // 부산
```


> **여러 변수가 하나의 객체를 공유하는 것을 막을 방법은 없다.** `Address b = a;`  
> 객체의 공유가 꼭 필요할 때도 있지만, 공유로 인한 이러한 사이드 이펙트가 발생할 수 있다.
> 
> 이 문제를 해결하는 것이 ***불변 객체*** 이다.


## 불변 객체의 도입

---

> 문제는 공유 참조가 아닌, 공유 참조의 값을 변경했을 때 발생한다.

### 불변 객체
객체의 상태 (내부 값, 필드, 멤버변수)가 변하지 않는 객체
  - 어떻게든 필드 값을 변경할 수 없게 설계한 것이 불변 객체 클래스가 된다.
```java
public class ImmutableAddress {
  
  private final String value;
  
  public ImmutableAddress(String value) {
    this.value = value;
  }
}
```

> `setter()` 메서드 자체를 제거하면, 값을 변경하는 것이 불가능 하여 어쩔 수 없이 새로운 인스턴스를 만들고 이 새로운 값을 참조하게 되도록 설계할 수 있다.

<br>

> Q. 전부 다 불변 객체로 만들어야 하나요?  
> A. 변경되면 안되는 값인 경우, 하나를 바꿨을 때 다른 것이 바뀌면 안되는 경우. 그러한 상황에 맞추어서 사용해야한다.

## 불변 객체의 값 변경

---

```java
public class ImmutableObj {
  
  private final int value;
  
  public ImmutableObj(int value) {
    this.value = value;
  }
  
  public ImmutableObj add(int addValue) {
    int result = this.value + addValue;
    return new ImmutableObj(result);
  }
}
```
불변 객체의 값을 변경하면 안된다. 따라서, 새로운 객체를 만들어서 반환한다.
이러한 방법으로 기존 객체를 유지할 수 있다.

