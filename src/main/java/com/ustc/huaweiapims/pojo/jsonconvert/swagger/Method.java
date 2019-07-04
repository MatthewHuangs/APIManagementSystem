package com.ustc.huaweiapims.pojo.jsonconvert.swagger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:10
 */
public class Method {
    Map<String, Operation> _map = new HashMap<>(); // string:"get"/"post"

    public Map<String, Operation> get_map() {
        return _map;
    }

    public void set_map(Map<String, Operation> _map) {
        this._map = _map;
    }
}
