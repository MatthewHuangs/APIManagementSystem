package com.ustc.huaweiapims.pojo.jsonconvert.postman;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 14:28
 */
public class PostmanOperation {
    private String name = null; // get("paths").get("/{顶级url}").get("get/post/put").get("summary")
    private Request request = null;
    private List<String> response = new ArrayList<>();

    public PostmanOperation(String name, Request request) {
        this.name = name;
        this.request = request;
    }

    public PostmanOperation() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }
}

