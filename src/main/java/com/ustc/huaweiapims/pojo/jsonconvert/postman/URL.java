package com.ustc.huaweiapims.pojo.jsonconvert.postman;



import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 14:31
 */
//public class URL {
//    private Map<String, Object> urlMap = new HashMap<>();
//
//    public Map<String, Object> getUrlMap() {
//        return urlMap;
//    }
//
//    public void setUrlMap(Map<String, Object> urlMap) {
//        this.urlMap = urlMap;
//    }
//}

@JsonInclude(JsonInclude.Include.NON_NULL)
public class URL {
    private String raw = null;
    private String protocol = null;
    private List<String> host = null;
    private String port = null;
    private List<String> path = null;
    private List<KeyValue> query = null;
    private List<KeyValue> variable = null;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<KeyValue> getQuery() {
        return query;
    }

    public void setQuery(List<KeyValue> query) {
        this.query = query;
    }

    public List<KeyValue> getVariable() {
        return variable;
    }

    public void setVariable(List<KeyValue> variable) {
        this.variable = variable;
    }
}
