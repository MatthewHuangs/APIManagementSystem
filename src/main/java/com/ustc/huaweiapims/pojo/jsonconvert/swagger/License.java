package com.ustc.huaweiapims.pojo.jsonconvert.swagger;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 19:52
 */
public class License {
    private String name = null;
    private String url = null;

    public License(String name, String url) {
        this.name = name;
        this.url = url;
    }
    
    public License() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
