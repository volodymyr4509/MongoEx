package com.mongoex.volodymyr.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by vkret on 24.10.16.
 */
public class SecuredUser extends User {

    private String id;

    public SecuredUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String id) {
        super(username, password, authorities);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
