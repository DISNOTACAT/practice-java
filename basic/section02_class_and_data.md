# Section02 : 클래스와 데이터
## 클래스의 선언
- 클래스 : 설계도 (타입)
- 객체(인스턴스) : 설계도 기반으로 메모리에 만들어진 실체

<br>

- 객체 생성(선언) : ```student1 = new Student();```
  - 선언 후, ```student1```에는 참조값(마치 주소값 ```x001```)을 보관. 이를 통해 메모리에 있는 실제 인스턴스를 접근하고 관리할 수 있다.

<br>

- 객체의 독립성 : 객체는 클래스에서 정의한 속성과 기능을 가진 실체. 서로 독립적인 상태
- 용어상 인스턴스는 관계에 초점을 맞춘 단어.

## 배열의 대입
- **자바에서 대입은 항상 변수에 들어있는 값을 복사해서 전달한다.**
- 인스턴스가 복사되는 것이 아닌 참조값만 복사된다.
