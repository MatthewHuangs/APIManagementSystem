package com.ustc.huaweiapims.pojo.jsonconvert.postman;

import java.util.List;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 14:28
 */
public class Request {
    private String method = null;
    private List<KeyValue> header = null;
    private Body body = new Body();
    private URL url = new URL();
    private String description = null;

    public Request(String method, List<KeyValue> header, Body body, URL url, String description) {
        this.method = method;
        this.header = header;
        this.body = body;
        this.url = url;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Request() {}

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<KeyValue> getHeader() {
        return header;
    }

    public void setHeader(List<KeyValue> header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}

