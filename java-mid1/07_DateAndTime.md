# 날짜와 시간
### 라이브러리가 필요한 이유
1. 날짜와 시간 차이 계산
2. 윤년 계산
3. 일광 절약 시간(Daylight Saving Time, DST) 변환
4. 타임존 계산
   - 기준 시간 00:00 (London / UTC / GMT)
   > UTC 는 원자시계를 사용하여 정밀한 시간 측정과 국제적인 표준에 관해서는 UTC가 선호된다.
   - 서울 Asia/Seoul, UTC+9
   > 여기서 서로 다른 나라의 현지시간을 UTC 기준으로 계산하고, DST 또한 적용해 주어야 한다.

### 자바 날짜와 시간 라이브러리 역사
**JDK 1.0(`java.util.Date`)**
- 문제점
  - 타임존 처리 부족
  - 불편한 날짜 시간 연산
  - 불변 객체 부재 (Date 객체가 mutale)
- 해결책
  - JDK 1.1에서 `java.util.Calendar` 클래스 도입으로 타임존 지원 개선
  - 날짜 시간 연산을 위한 추가 메소드 제공
  
**JDK 1.1(`java.util.Calendar`)**
- 문제점
  - 사용성 저하
  - 성능 문제
  - 불변 객체 부재
- 해결책
  - Joda-Time 오픈소스 라이브러리 도입

**Joda-Time**
- 문제점 : 표준 라이브러리가 아님
- 해결책 : 자바 8에서 `java.time` 패키지를 표준 API로 도입

**JDK 8(1.8) (`java.time` 패키지)**
- `LocalDate`, `LocalTIme`, `LocalDateTime`, `ZonedDateTime`, `Instant` 등의 클래스를 포함
- Joda-Time의 많은 기능을 표준 자바 플랫폼으로 가져옴

> Joda-Time 오픈소스 개발자를 데려와서 자바 표준으로 만들어냄. 마찬가지로 표준 ORM의 불편으로 하이버네이트 오픈 소스가 공개되고, 해당 개발자를 데려와서 표준을 만듦 (JPA)


### 자바 날짜와 시간 라이브러리
https://docs.oracle.com/javase/tutorial/datetime/iso/overview.html

`LocalTime`, `LocalDate`, `LocalDateTime`
- Local 이 붙는 이유는 세계 시간대를 고려하지 않아서 타임존이 적용되지 않기 때문이다. 특정 지역의 날짜와 시간만 고려할 때 사용한다. (국내 서비스)

`ZonedDateTime`
- 타임존을 포함한 시간대를 고려한 날짜와 시간으로 일광 절약 시간제가 적용된다. 
- 2013-11-21T08:20:30,213+9[Asia/Seoul]

`OffsetDateTime`
- 타임존은 없고 시간대 차이인 고정된 오프셋만 포함됨. 일광 절약 시간제가 적용되지 않음


### Instant  
UTC를 기준으로 하는, 시간의 한 지점을 나타낸다. 1970년 1월 1일 0시 0분 0초(UTC)를 기준으로 경과한 시간으로 계산

### 시간의 간격 (Amount of time)
`Period` : 두 날짜 사이의 간격을 년,월,일 단위로 나타냄  
`Duration` : 두 시간 사이의 간격을 시, 분 초(나노초) 단위로 나타냄

## 기본 날짜와 시간 - LocalDateTime

```java
import java.time.LocalDate;

LocalDate ofDate = LocalDate.of(2025,6,24);
LocalDate newDate = ofDate.plusDays(10); // 모든 날짜 클래스는 불변으로 새로운 객체를 반환하기 때문에 반환값을 꼭 받아야 한다.
```

### isEquals() vs equals()
- isEquals()는 단순히 비교 대상이 시간적으로 같으면 true 반환
  - 객체가 다르고, 타임존이 달라고 시간 적으로 같으면 반환
  - 예) 서울 09:00 == UTC 00:00 (둘이 서로 전화하면 동일한 시간대)
- equals()는 객체 타입, 타임존 등 내부 데이터의 모든 구성요소가 같아야 true 반환
  - 예) 서울 09:00 != UTC 00:00 (서로 다른 타임존)

## 타임존 - ZonedDateTime
자바는 타임존을 `ZoneId` 클래스로 제공한다.
`ZoneId` 내부에 일광 정략 시간 관련 정보, UTC와의 오프셋 정보를 포함하고 있다.
```java
  public static void main(String[] args) {
    for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
      ZoneId zoneId = ZoneId.of(availableZoneId);
//      System.out.println(zoneId + " | " + zoneId.getRules()) ; // Asia/Aden | ZoneRules[currentStandardOffset=+03:00]
    }

    ZoneId zoneId = ZoneId.systemDefault();
    System.out.println("ZoneId.systemDefault = "
        + zoneId); // ZoneId.systemDefault = Asia/Tokyo

    ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
    System.out.println("seoulZone = " + seoulZoneId);
  }
}
```
- ZoneId를 활용해서 LocalDateTime 변환
```java
    LocalDateTime ldt = LocalDateTime.of(2030,1,1,13,30,00);
    ZonedDateTime zdt1 = ZonedDateTime.of(ldt, ZoneId.of("Asia/Seoul"));
    System.out.println(zdt1); // 2030-01-01T13:30+09:00[Asia/Seoul]

    ZonedDateTime zdt2 = zdt1.withZoneSameInstant(ZoneId.of("UTC"));
    System.out.println(zdt2); // 2030-01-01T04:30Z[UTC]
```

### ZoneDateTime vs OffsetDateTime
- `ZoneDateTime`은 구체적인 지역 시간대를 다룰 때 사용, 일광 절약 시간을 자동으로 처리할 수 있다. 사용자 지정 시간대에 따른 시간 계산이 필요할 때 적합하다. (달력, 약속 시간 등 사용자 고려)
- `OffsetDateTime`은 UTC와의 시간 차이만을 나타낼 떄 사용하며, 지역 시간대의 복잡성을 고려하지 않는다. 시간대 변환 없이 로그를 기록하고, 데이터를 저장하고 처리할 떄 적합하다. (지역 차이 없이 명시를 위한 값)


## 기계 중심의 시간 - Instant
Instant 내부에는 초 데이터만 들어있다.

> Epoch 시간  
> Epoch time(에포크 시간) 또는 Unix timestamp는 컴퓨터 시스템에서 시간을 나타내는 방법 중 하나이다. 1970년 1월 1일 00:00:00 UTC로부터 현재까지 경과한 시간을 초단위로 표현한다.  
> 즉, 시간대에 영향을 받지 않는 절대적인 시간 표현 방식이다.  

### 장점
- 시간대 독립성
- 고정된 기준점
### 단점
- 사용자 친화적이지 않음
- 시간대 정보 부재

### 사용 예
- 전 세계적인 시간 기준 필요시 (로그 기록, 트랜잭션 타임스탬프, 서버간의 시간 동기화 등)
- 시간대 변환 없이 시간 계산 필요시
- 데이터 저장 및 관리


## 기간, 시간의 간격 -Duration, Period
- Period : `getYear()`, `getMonth()`,` getDays()`
- Duration : `toHours()`, `toMinutes()`, `getSeconds()`, `getNano()`
> Period 는 yesr, month, days 가 들어있지만 duration는 초만 있어서 계산해서 hours, minutes 를 나타내기 때문에 to~() 메서드명을 가진다.


## 날짜와 시간의 핵심 인터페이스
날짜와 시간은 특정 시간(시각)과 시간의 간격(기간)으로 나눌 수 있다.
- 특정 시점의 시간(시각)
  - 다음 회의는 11시 30분에 진행한다.
  - 내 생일은 11월 15일이댜.
- 시간의 간격(기간, 시간의 양)
  - 앞으로 4년은 더 공부해야해
  - 이 프로젝트는 3개월 남았어.

- 특정 시점의 시간 : `Temporal`(`TemporalAccessor` 포함) 인터페이스를 구현한다.
  - `LocalDateTime`, `LocalDate`, `LocalTime`, `ZonedDateTime`, `OffsetDateTime`, `Instant`
- 시간의 간격(기간) : `TemporalAmount` 인터페이스를 구현한다.
  - `Period`, `Duration`

### TemporalAccessor 인터페이스
- 날짜와 시간을 읽기 위한 기본 인터페이스

### Temporal 인터페이스
- TemporalAccessor의 하위 인터페이스로, 날짜와 시간을 조작하기 위한 기능을 제공

### TemporalAmount 인터페이스
- 시간의 간격을 나타내며, 날짜와 시간 객체에 적용하여 그 객체를 조정할 수 있다

## 시간의 단위와 시간 필드
- TemporalUnit(ChronoUnit) : 날짜와 시간의 단위
- TemporalField(ChronoField) : 시간과 각 필드

### 시간의 단위 - TemporalUnit (ChronoUnit)
- TemporalUnit 은 인터페이스로 날짜와 시간을 측정하는 단위를 나타낸다.
- 주로 구현체는 ChronoUnit 이다.
- ChronoUnit을 사용하면 두 날짜 또는 시간 사이의 차이를 해당 단위로 쉽게 계산할 수 있다.

### 시간 필드 - ChronoField
ChronoField는 날짜 및 시간을 나타내는 데 사용되는 열거형이다.  
이 열거형은 다양한 필드를 통해 날짜와 시간의 특정 부분을 나타낸다. 여기에는 연도, 월, 일, 시간, 분 등이 포함된다.
- TemporalField 인터페이스는 날짜와 시간을 나타내는 데 사용 된다.
- ChronoField 구현체를 주로 사용하고, 열거형으로 구현되어있다.
  - 다양한 필드를 통해 날짜와 시간의 특정 부분을 나타낸다.
  - Field란 날짜와 시간 중에 있는 특정 필드를 의미한다.
  - 예) 2024년 8월 16일 -> YEAR: 2024 / MONTH_OF_YEAR: 8 / DAY_OF_MONTH: 16

> 단순히 시간의 단위 하나하나를 뜻하는 ChronoUnit과는 다른 것을 알 수 있다. ChronoField를 사용해야 날짜와 시간의 각 필드 중에 원하는 데이터를 조회할 수 있다.

## 날짜와 시간 조회하고 조작하기

- `TemporalAccessor.get(TemporalField field)`
  - LocalDateTime을 포함한 특정 시점의 시간을 제공하는 클래스는 모두 TemporalAccessor 인터페이스를 구현한다.
  - `get(TemporalField field)`을 호출할 때 어떤 날짜와 시간 필드를 조회할 지 TemporalField의 구현인 ChronoField를 인수로 전달하면 된다.
- 편의메서드가 제공된다. (없는 경우는 직접 작성)

- `Temporal plus(long amountToAdd, TemporalUnit unit)`
  - LocalDateTime을 포함한 특정 시점의 시간을 제공하는 클래스는 모두 Temporal 인터페이스를 구현한다.
  - 불변이므로 반환 값을 받아야 한다.
  - `minus()`
- 편의메서드가 제공된다.

### 안되는 경우
```java
  LocalDate now = LocalDate.now();
    int minute = now.get(ChronoField.SECOND_OF_MINUTE);
    System.out.println("minute = " + minute); // .UnsupportedTemporalTypeException
```
- 확인하기
```java
boolean supported = now.isSupported(ChronoField.SECOND_OF_MINUTE);
```

### with()
```java
    LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 59);
    System.out.println("dt = " + dt);

    // 기존 값의 일부를 변형시키고 새로운 객체를 만들떄 (불변이기 때문에 객체 반환)
    LocalDateTime changedDt1 = dt.with(ChronoField.YEAR, 2020);
    System.out.println("changedDt1 = " + changedDt1);

    LocalDateTime changedDt2 = dt.withYear(2020);
    System.out.println("changedDt2 = " + changedDt2);
```

- `Temporal with(TemporalField, long newValue)`
  - Temporal.with()를 사용하면 날짜와 시간의 특정 필드의 값만 변경할 수 있다.
  - 불변이므로 반환 값을 받아야 한다.
- 편의 메서드 제공

- `TemporalAdjuster`
  - with()는 아주 단순한 날짜만 변경할 수 있다. 복잡한 날짜를 계산하고 싶다면 TemporalAdjuster를 사용하면 된다.
```java
    // TemporalAdjuster 사용
    // 다음주 금요일
    LocalDateTime with1 = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    System.out.println("기준 날짜 = " + dt);
    System.out.println("다음주 금요일 = " + with1);

    // 이번 달의 마지막 일요일
    LocalDateTime with2 = dt.with(
        TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
    System.out.println("같은 달의 마지막 일요일 = " + with2);
```

## 날짜와 시간 문자열 포맷팅
- 많이 씀
```java
// 포맷팅 : 날짜를 문자로
    LocalDate date = LocalDate.of(2024, 12, 13);
    System.out.println("date = " + date);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    String fomattedDate = date.format(formatter);
    System.out.println("fomattedDate = " + fomattedDate);

    // 파싱 : 문자를 날짜로
    String input = "2040년 01월 01일";
    System.out.println("input = " + input);
    LocalDate parseDate = LocalDate.parse(input, formatter);
    System.out.println("parseDate = " + parseDate);
```
