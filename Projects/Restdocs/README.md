# Spring REST docs

## 설명

Spring REST docs 라이브러리를 이용해 Api 문서를 자동화

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
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```