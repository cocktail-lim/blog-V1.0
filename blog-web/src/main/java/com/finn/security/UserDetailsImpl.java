package com.finn.security;

import com.finn.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/*
 * @description:
 * @author: Finn
 * @create: 2022-01-16-11-18
 */

/*
* getAuthorities: 获取用户权限，一般情况下获取到的是用户的角色信息。
* getCredentials: 获取证明用户认证的信息，通常情况下获取到的是密码等信息。
* getDetails: 获取用户的额外信息，（这部分信息可以是我们的用户表中的信息）。
* getPrincipal: 获取用户身份信息，在未认证的情况下获取到的是用户名，在已认证的情况下获取到的是 UserDetails。
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetailsImpl implements UserDetails {
    private User user;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
