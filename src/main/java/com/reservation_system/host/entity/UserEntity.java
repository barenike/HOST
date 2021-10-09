package com.reservation_system.host.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class UserEntity {
    // Не факт, что будет работать код с 12 строки по 17
    @Id
    @GeneratedValue(generator = "uuid")
    // name - название перемнной к которой мы хотим обратиться в БД?
    // strategy - см. в Google
    @GenericGenerator(name = "user_id", strategy = "uuid2")
    private UUID user_uuid;
    private String login;
    private String password;

    public UserEntity(UUID user_uuid, String login, String password) {
        this.user_uuid = user_uuid;
        this.login = login;
        this.password = password;
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
