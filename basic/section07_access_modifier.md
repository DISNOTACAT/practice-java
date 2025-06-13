### 접근 제어자
- private
- default (package-private)
- protected
- public
> 접근제어자의 핵심은 속성과 기능을 외부로부터 숨기는 것이다.

<br>

## 클래스 레벨의 접근 제어자 규칙
- 클래스 레벨에는 public, default만 사용 가능
- public 클래스는 반드시 파일명과 이름이 같아야 한다.
  - 하나의 자바 파일에
    - public class 단 한개
    - default class 무한정

# 캡슐화
> 캡슐화를 안전하게 완성할 수 있게 해주는 장치가 바로 접근 제어자다.
- 데이터를 숨겨라
  - 객체의 데이터는 객체가 제공하는 메서드를 통해서 접근해야 한다.
- 기능을 숨겨라
  - 사용자 입장에서 꼭 필요한 기능만 노출, 너무 많은 것을 알아야 할 필요는 없다.

> early return
> 선 검증 로직(early return) -> 후 실행 로직