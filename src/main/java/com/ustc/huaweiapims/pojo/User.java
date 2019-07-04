package com.ustc.huaweiapims.pojo;

import java.io.Serializable;

/**
 * @author matthew huang
 * @description
 * @date 2019/5/22 3:50 PM
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String accessToken;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User() {
    }

    public User(Long id, String name, Integer age, String accessToken) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
