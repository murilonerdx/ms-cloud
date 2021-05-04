package com.mscloud.hruser.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
            @JoinTable(
                    name="tb_user_role",joinColumns = @JoinColumn(name="user_id"),
                    inverseJoinColumns = @JoinColumn(name="role_id")
            )
    Set<Role> roles = new HashSet<>();

    public User(){}

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
