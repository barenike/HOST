package com.reservation_system.host.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private UUID user_uuid; // Будем генерировать при создании объекта: UUID uuid = UUID.randomUUID();

    @Column(name = "e-mail")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin;

    public UserEntity(UUID user_uuid, String login, String password) {
        this.user_uuid = user_uuid;
        this.login = login;
        this.password = password;
    }

    public UserEntity(UUID user_uuid, String login, String password, boolean isAdmin) {
        this.user_uuid = user_uuid;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public UserEntity() {

    }

    public UUID getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(UUID user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    //Пусть будет - пригодится
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        return Objects.equals(user_uuid, that.user_uuid);
    }

    @Override
    public int hashCode() {
        return user_uuid != null ? user_uuid.hashCode() : 0;
    }
}
