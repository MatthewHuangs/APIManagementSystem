package com.ustc.huaweiapims.pojo.jsonconvert.postman;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 14:30
 */
//public class Body {
//    private Map<String, Object> bodyMap = new HashMap<>();
//
//    public Map<String, Object> getBodyMap() {
//        return bodyMap;
//    }
//
//    public void setBodyMap(Map<String, Object> bodyMap) {
//        this.bodyMap = bodyMap;
//    }
//}


public class Body {
    private String mode = null;
    private String raw = null;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
