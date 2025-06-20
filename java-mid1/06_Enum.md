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
