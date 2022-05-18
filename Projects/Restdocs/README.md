# Spring REST docs

## 설명

Spring REST docs 라이브러리를 이용해 Api 문서를 자동화

## 개발 환경

- OS - macOS(ARM - M1)
- Editor - IntelliJ IDEA
- Version
    - JDK - 11(Zulu 11.0.12)
- DataBase - H2

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

## Preview

![Preview](preview.gif)

## 후기

테스트 코드로 문서를 자동화 하기 때문에 컨트롤러, DTO를 건들일 필요가 없다.(Swagger의 단점 보완)

하지만 build.gradle에서 함수를 구성해야 정상적으로 작동된다. 이 부분에서 어려움이 많았던 것 같다.

앞으로 문서화를 할 일이 있다면 Swagger보다 Spring Rest docs를 쓸 것 같다.

## 참고 자료

- [https://techblog.woowahan.com/2597/](https://techblog.woowahan.com/2597/)
- [https://xlffm3.github.io/spring%20&%20spring%20boot/rest-docs/](https://xlffm3.github.io/spring%20&%20spring%20boot/rest-docs/)
- [https://github.com/spring-projects/spring-restdocs/issues/737](https://github.com/spring-projects/spring-restdocs/issues/737)