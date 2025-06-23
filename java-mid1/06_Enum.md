# 열거형 Enum

## 문자열과 타입 안정성
```java
// 문자열 비교시, 존재하지 않는 등급을 요청한 경우
int vip = discountService.discount("VIP", totalPrice);
System.out.println("vip 할인 금액 = " + vip);
```
SILVER, GOLD, DIAMOND 등급제에서
- 존재하지 않는 등급을 요청 
- 오타가 포함된 요청
- 소문자로 요청한 경우

또한 위와 같은 경우는 컴파일시에는 오류가 발생하지 않는다. 정확한 문자만 메서드에 전달이 되어야하는데, 이 문제를 String으로는 해결할 수 없는 문제가 있다.

```java
public class StringMembership {
  public static final String silver = "SILVER";
  public static final String gold = "GOLD";
}
```
문자열 상수를 활용하면 인자가 명확해지고 컴파일 문제도 해결할 수 있다.
하지만 매개변수로 String을 받을 수 있도록 해두었기 때문에, 개발자가 임의로 선언을 해서 전달하면 발생하는 문제를 막을 수 없다.
> 매개변수에 String 이 아닌, SIlVER/GOLD..만 받을 수 있는 타입을 사용해야함


## 타입 안전 열거형 패턴
> 나열한 항목만 사용이 가능하다.

- static으로 생성된 각 멤버십 별의 인스턴스의 참조값을 따르게되어. 이름에 따른 컴파일 오류를 방지하고, 동일한 참조값을 참조하여 다른 멤버십으로 변환되는 것을 방지한다.

  - 직접 구현한 grade
    ```java
    public class ClassGrade {
    
    public static final ClassGrade BASIC = new ClassGrade();
    public static final ClassGrade GOLD = new ClassGrade();
    public static final ClassGrade DIAMOND = new ClassGrade();
    
    }
    // 외부 생성 방지
    private ClassGrade() {}
    ```
  - service
      ```java
    public class DiscountService {
    
    public int discount(ClassGrade classGrade, int price) {
    
        int discountPercent = 0;
    
        if(classGrade == ClassGrade.BASIC) {
          discountPercent = 10;
        } else if (classGrade == ClassGrade.GOLD) {
          discountPercent = 20;
        } else if (classGrade == ClassGrade.DIAMOND) {
          discountPercent = 30;
        }
    
        return price + discountPercent / 100;
        }
      }
      ```
    
> 하지만 위 방식에서는 직접 구현을 할 수 있다는 문제가 발생한다. `ClassGrade newGrade = new ClassGrade()`  
> 이것을 방지하기 위해 내부에 `private ClassGrade() {}`를 반드시 선언해준다.


### 타입 안전 열거형 패턴의 장점
- 타입 안정성 향상
- 데이터 일관성
> 제한된 인스턴스 생성을 통한 = 미리 정의된 값만 활용


# 열거형 Enum Type
타입 안전 열거형 패턴을 쉽게 사용할 수 있도록 함  
- 열거형도 클래스다
- private 생성자가 되어있어, 외부에서 생성할 수 없다.
- `java.lang.Enum` 을 상속받는다.
- `toString()`을 재정의한다.
- 열거형은 `switch`문에서 사용할 수 있다는 장점이 있다.

### 열거형의 장점
- 타입 안정성 향상
- 간결성 및 일관성
- 확장성
- static import 를 활용하여 더 읽기 좋게 작성가능 `Grade.BASIC -> BASIC`

## 주요 메서드
- `values()` : Enum 클래스의 항목을 하나씩 배열로 반환.
  - `value.name()`, `value.ordinal()`
- `valueOf(String str)` : String 을 Enum 반환
- `toString()` : enum 상수의 이름을 반환

> **주의 ordinal()은 가급적 사용하지 않는다.**
> 상수 선언 위치가 변경되면, 숫자가 변경될 수 있다.
