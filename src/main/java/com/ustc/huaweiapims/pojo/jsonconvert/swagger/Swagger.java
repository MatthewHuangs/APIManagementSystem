package com.ustc.huaweiapims.pojo.jsonconvert.swagger;

import java.util.List;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:08
 */
public class Swagger {
    private String swagger = "2.0";
    private Info info = null;
    private String host = null;
    private String basePath = null;
    private List<Tag> tags = null;
    private Path paths = null;

    public String getSwagger() {
        return swagger;
    }

    public void setSwagger(String swagger) {
        this.swagger = swagger;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Path getPaths() {
        return paths;
    }

    public void setPaths(Path paths) {
        this.paths = paths;
    }
}
