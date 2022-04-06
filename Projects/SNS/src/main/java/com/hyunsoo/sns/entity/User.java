package com.hyunsoo.sns.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseTime implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long following;

    @Column(nullable = false)
    private Long follower;

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.following = 0L;
        this.follower = 0L;
    }

    public void increaseFollowing() {
        this.following++;
    }

    public void increaseFollower() {
        this.follower++;
    }

    public void decreaseFollowing() {
        this.following--;
    }

    public void decreaseFollower() {
        this.follower--;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
