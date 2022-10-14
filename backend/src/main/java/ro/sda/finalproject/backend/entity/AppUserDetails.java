package ro.sda.finalproject.backend.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class AppUserDetails implements UserDetails {

    private AppUser appUser;

    public AppUserDetails(AppUser appUser){
    this.appUser = appUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Roles> roles = appUser.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoles().name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
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
