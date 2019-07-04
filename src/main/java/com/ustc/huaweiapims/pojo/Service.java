package com.ustc.huaweiapims.pojo;

import java.io.Serializable;

/**
 * @Author Matthew Huang
 * @Date 2019-5-23 10:08
 */
public class Service implements Serializable {

    private Long id;
    private String name;
    private String type;
    private Double time;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Service() {
    }

    public Service(Long id, String name, String type, Double time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", time=" + time +
                '}';
    }
}
