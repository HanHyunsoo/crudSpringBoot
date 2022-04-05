package com.hyunsoo.sns.controller;

import com.hyunsoo.sns.dto.request.LoginRequest;
import com.hyunsoo.sns.dto.request.UserCreateRequest;
import com.hyunsoo.sns.entity.User;
import com.hyunsoo.sns.jwt.JwtAuthenticationProvider;
import com.hyunsoo.sns.model.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestBody final UserCreateRequest userCreateRequest) {

        User user = (User) userService.loadUserByUsername(userCreateRequest.getUsername());

        // username이 이미 db에 존재하면 409 status를 반환
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username이 이미 존재합니다.");
        }

        // password1과 password2가 다르면 403 status 반환
        if (!userCreateRequest.getPassword1().equals(userCreateRequest.getPassword2())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("password1과 password2이 다릅니다.");
        }

        userService.save(userCreateRequest);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody final LoginRequest loginRequest,
                                        final HttpServletResponse response) {

        User user = (User) userService.loadUserByUsername(loginRequest.getUsername());

        // user가 없으면 404에러 반환
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 아이디는 가입되어 있지 않습니다.");
        }

        // request로 받은 password와 기존 유저의 password가 다르면 400 반환
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtAuthenticationProvider.createToken(loginRequest.getUsername());
        response.setHeader("Authorization", "Bearer " + token);

        return ResponseEntity.ok(user.getUsername() + "님으로 로그인 했습니다.\n" + token);
    }

    // 로그아웃 메소드
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(final HttpServletResponse response) {
        response.setHeader("Authorization", null);

        return ResponseEntity.ok("로그아웃 성공");
    }

    // 현재 로그인 된 user 확인
    @GetMapping("/info")
    public ResponseEntity<String> getInfo(@AuthenticationPrincipal Object user) {

        return ResponseEntity.ok("" + user);
    }
}
