package com.reservation_system.host.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(unique = true, name = "user_id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public UserEntity(String email, String password, RoleEntity roleEntity) {
        this.email = email;
        this.password = password;
        this.roleEntity = roleEntity;
    }

    public UserEntity() {

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    // replace IllegalArgumentException on a more suitable type of Exception.
    // Custom IllegalEmailException + proper catch in the UserController
    public void setEmail(String email) throws IllegalArgumentException {
        final String regex = "[\\w!#$%&'.*+/=?^`{|}~-]*@hostco\\.ru";
        final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getPassword() {
        return password;
    }

    // replace IllegalArgumentException on a more suitable type of Exception.
    // IllegalPasswordException + proper catch in the UserController
    public void setPassword(String password) throws IllegalArgumentException {
        final String regex = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)\\S{8,}";
        final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            this.password = password;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
