package com.ustc.huaweiapims.pojo.jsonconvert.postman;

import java.util.List;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:07
 */
public class Postman {
    private Info info = null;
    private List<API> item = null;

    public Postman(Info info, List<API> item) {
        this.info = info;
        this.item = item;
    }

    public Postman() {}

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<API> getItem() {
        return item;
    }

    public void setItem(List<API> item) {
        this.item = item;
    }
}
