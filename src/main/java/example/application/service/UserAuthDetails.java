package example.application.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import example.domain.model.user.User;
import example.domain.model.user.UserId;

public class UserAuthDetails implements UserDetails{

    private final User user;

    public UserAuthDetails(User user){
        this.user = user;
    }

    public UserId getUserId(){
        return user.GetUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_"+user.getUserRole().GetValue());
    }

    @Override
    public String getPassword() {
        return user.getPassword().GetValue();
    }

    @Override
    public String getUsername() {
        return user.GetUserName().Getvalue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
