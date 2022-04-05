package com.hyunsoo.sns.model;

import com.hyunsoo.sns.dto.request.UserCreateRequest;
import com.hyunsoo.sns.entity.User;
import com.hyunsoo.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    // username으로 UserDetails 엔티티를 불러오는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // User Entity를 저장하는 메소드
    public void save(UserCreateRequest request) {
        User entity = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword1()))
                .build();

        userRepository.save(entity);
    }
}
