package pl.mdyrkacz.homepageapp.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class CurrentUser extends User {
    private final pl.mdyrkacz.homepageapp.user.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.mdyrkacz.homepageapp.user.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.mdyrkacz.homepageapp.user.User getUser() {
        return user;
    }
}