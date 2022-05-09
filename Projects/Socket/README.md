# Socket

## 설명

Spring Boot로 웹소켓 만들기

## 개발 환경

- OS - macOS(ARM - M1)
- Editor - IntelliJ IDEA
- Version
    - JDK - 11(Zulu 11.0.12)

## dependencies

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-websocket'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

## 참고

* [블로그](https://daddyprogrammer.org/post/4077/spring-websocket-chatting/)