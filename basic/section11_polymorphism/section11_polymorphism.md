# 다형성
한 객체가 여러 타입의 객체로 취급될 수 있는 능력
- 다형적 참조
- 메서드 오버라이딩


## 다형적 참조
```Parent ploy = new Child()``` 부모가 자식을 참조할 수 있다.   
부모 타입은 자신은 물론이고, 자신을 기준으로 하위 모든 자식 타입을 참조할 수 있다.

호출한 타입으로 참조값을 조회한다. 위 코드의 ```poly.childMethod()``` 하게 되면 컴파일 오류가 발생한다.
1. ```poly```를 호출 할 때 Parent 타입으로 호출했었고,  
2. 참조할 때 ```Parent```를 먼저 찾아가게 된다.
3. ```childMethod()```는 자식에게 있는 메서드로 Parent 내에 찾을 수 없다.
4. 부모는 자식의 메서드를 찾아갈 수 없으므로 참조할 수 있는 메서드는 없는 것이 된다.

그렇다면 자식의 메서드를 쓰고 싶으면 어떻게 해야할까? -> 캐스팅

# 다형성과 캐스팅
## 다운캐스팅
```java
Child child = (Child) poly;
poly.childMethod();
```  
다운캐스팅을 작성하지 않으면 오류가 난다.  왜? 자식은 부모를 품을 수 없으니까  

호출 타입을 Child 타입으로 강제로 변경해서 ```Child```참조값으로 ```poly```를 확인하는 것이다.  
> **```poly```의 타입을 변경하는 것이 아니다.**   

```Parent```타입의 ```poly```를 일시적으로 Child 타입으로 변경하여 대입하는 것이다.  
다운캐스팅을 통해, 해당 인스턴스를 차아간 다음 ```Child```타입을 찾고 ```childMethod()```를 찾을 수 있게 된다.

- 업캐스팅 : 부모 타입으로 변경
- 다운캐스팅 : 자식 타입으로 변경


## 캐스팅의 종류
### 일시적 다운캐스팅
```java
((Child) poly).childMethod();
```
해당 메서드를 호출하는 순간만 다운캐스팅

### 업캐스팅
```java
Child child = new Child();
Parent parent1 = (Parent) child;
Parent parent2 = child; // 업캐스팅 생략 가능
```

> 업캐스팅은 생략할 수 있고, 매우 많이 사용하기 때문에 생략을 권장한다.  
> 자바에서 부모는 자식을 담을 수 있지만 반대는 안된다.
> 왜 다운캐스팅은 안되는 걸까?


## 다운캐스팅 주의점
```java
Parent parent = new Parent();
Child poly = (Child) parent; // 런타임 오류 발생
```
인스턴스를 생성할 때, Parent 만 생성한다! 따라서 다운캐스팅을 하더라도 참조하러 갔을 때 Child 정보가 없기 때문에 런타임 오류가 발생한다. ClassCastException 발생

> **업캐스팅이 안전한 이유**  
> 인스턴스 생성시 상위 타입은 반드시 생서된다. 따라서 참조에 문제가 없음.  
> 하지만 다운캐스팅의 경우 생성되지 않은 하위 타입으로 캐스팅할 경우 오류가 발생한다.

## instanceof
변수가 참조하고 있는 실제 타입을 확인 후, 캐스팅 처리
```java
private static void call(Parent parent) {
  if (parent instanceof Child) {
    Child child = (Child) parent;
    child.childMethod();
  }
}
```

### Java 16 - Pattern Matching for instaceof
```java
private static void call(Parent parent) {
  if (parent instanceof Child child) {
//    Child child = (Child) parent; // 변수를 위에서 함께 선언할 수 있다.
    child.childMethod();
  }
}
```


## 다형성과 메서드 오버라이딩
오버라이딩의 가치는 다형성과 함께 사용할 때 나타난다.

변수는 오버라이딩 되지 않는다. ```poly```를 ```Child```로 캐스팅하더라도  ```poly```는 원래 ```Parent```이기 때문에 ```Parent.value```를 참조하게됨

```poly.method()```를 호출할 경우,
1. ```poly```는 ```Parent``` 이기 때문에 ```Parent.method()```에 접근한다.
2. 하위 자식 중 ```method()```를 오버라이딩 한 것이 있는지 확인한다.
3. 재정의된 ```method()```가 있다면 해당 메서드를 호출한다. (없다면 ```Parent``` 진행)
따라서, 오버라이딩한 ```Child.method()```를 호출하게 된다.

> 오버라이딩 된 메서드는 항상 우선권을 가진다.

