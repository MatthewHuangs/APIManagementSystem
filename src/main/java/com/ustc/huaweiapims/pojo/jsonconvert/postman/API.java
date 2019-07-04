package com.ustc.huaweiapims.pojo.jsonconvert.postman;

import java.util.List;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 14:26
 */

public class API {
    private String name = null; 
    private List<PostmanOperation> item = null;
    private String description = null;

    public API(String name, List<PostmanOperation> item) {
        this.name = name;
        this.item = item;
    }

    public API() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostmanOperation> getItem() {
        return item;
    }

    public void setItem(List<PostmanOperation> item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

