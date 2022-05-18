# Swagger

## 설명

Swagger 라이브러리를 이용해 문서를 자동화

## 개발 환경

- OS - macOS(ARM - M1)
- Editor - IntelliJ IDEA
- Version
    - JDK - 11(Zulu 11.0.12)

## dependencies

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
    implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

## 후기

문서를 자동화 하는 것은 편하지만 DTO와 Controller에 어노테이션을 추가하는 방식이기 때문에 코드가 일관적이지 않음

대체 라이브러리인 Spring Rest Docs도 공부해봐야 겠음.