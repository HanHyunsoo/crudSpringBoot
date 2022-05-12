# Socket

## 설명

Web Socket을 고도화 하고 Stomp을 적용 해보자.

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
    implementation 'org.webjars.bower:bootstrap:4.3.1'
    implementation 'org.webjars.bower:vue:2.5.16'
    implementation 'org.webjars.bower:axios:0.17.1'
    implementation 'org.webjars:sockjs-client:1.1.2'
    implementation 'org.webjars:stomp-websocket:2.3.3-1'
    implementation 'com.google.code.gson:gson:2.8.0'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

## 참고

* [블로그](https://daddyprogrammer.org/post/4691/spring-websocket-chatting-server-stomp-server/)