package com.ustc.huaweiapims.pojo.jsonconvert.postman;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 14:29
 */
public class KeyValue {
    private String key = null;
    private String value = null;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

