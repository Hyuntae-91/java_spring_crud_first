# 학습 포인트 정리

## 2025-03-15

### Gradle 사용
1. Maven과 Gradle : 최근에는 Gradle 사용 비율이 더 높음 (빠른 빌드, Groovy/Kotlin DSL 의 유연성)
2. Maven 은 오래되거나 기존에 유지되던 서버에 사용되고 있을 수 있음

### 기본 웹 서버 실행
1. Spring Boot 의 기본 내장 웹서버는 Tomcat (별도 설정없이 실행)
2. 별도의 설정을 통해 다른 웹 서버로 변경 가능

### Spring Boot 의 어노테이션 역할
1. @SpringBootApplication 및 @Service 등을 이용하면, Spring 이 내부적으로 new Class() 하듯이 Bean 생성 및 관리
2. Component Scan 을 통해 필요한 클래스 스캔 후 Bean 등록

### 의존성 주입 (DI) 개념
1. 클래스 내부에서 new Class() 로 직접 생성 -> 강결합, 테스트 어려움
2. DI -> 외부에서 의존성(객체)를 주입 -> 느슨한 결합
   - Spring 이 객체 생성, 관리해주고 필요할 때 주입
3. @Autowired 생성자 주입 방식 학습함

### JPA 와 Hibernate, ORM 동작 원리
1. Spring Boot 내부적으로 Hibernate ORM 사용 확인
    - Python 의 SQLAlchemy 와 개념적으로 비슷함
2. 기본 CRUD 는 JpaRepository 인터페이스 상속만으로 자동 제공 됨 확인
3. 복잡한 쿼리 작성시
    - JPQL (엔티티 기반 쿼리)
    - Native Query (Raw SQL 사용 가능)
    - 매우 긴 쿼리같은 경우, MyBatis 와 같은 것을 연동해서 SQL 파일로 별도 관리 가능

### PreparedStatement 방식
1. @Query, @Param 어노테이션 사용으로 자동 바인딩 처리
   - python 에서 cursor.execute(query, param) 과 비슷한 역할을 함

### UTC 시간 설정
1. Python 과는 다르게, 전역으로 src/main/resources/application.properties 에 설정하여 관리
    - python 에서는 그때그때 utc로 timezone 변환 처리
    - python 방식이 유연하지만, 실수 가능성이 올라갈듯..?

### DB 테이블 네이밍 snake_case 적용
1. Java 의 명명규칙과 DB 의 명명 규칙이 snake_case 와 camelCase 로 다른 부분이 있음
2. 한쪽으로 맞추면 되기도 하지만, DB 입력시 변환하도록 처리도 가능
   - src/main/resources/application.properties 에 CamelCaseToUnderscoresNamingStrategy 전략 적용

### DB 테이블 네이밍 예약어 충돌 해결
1. @Table(name = "users") 로 테이블 예약어 충돌 해결
    ```
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console
    spring.jpa.hibernate.ddl-auto=update
   ```
   내용 추가

## 2025-03-18

### DTO 개념
- Data Transfer Object (DTO) : 데이터를 전달 할 때 사용하는 객체
- Controller 와 Entity 를 분리하여 강결합을 해결하기 위한 개념
- API 에서 Entity 를 받도록 하면, Entity 변경시, 의도치 않게 API 스펙이 변경되는 문제가 발생 할 수 있음
- Python 으로 따지면, Pydantic 과 BaseModel 클래스를 사용하여, Request, Response class 를 정의하는것과 동일한 개념
- DB 관련 처리에서 사용하는 Entity 와 Request, Response 에 사용하는 모델을 분리하는 개념
- validation 진행을 위해, build.gradle 에 아래를 추가
   ```
   implementation 'org.springframework.boot:spring-boot-starter-validation'
   ```
  
