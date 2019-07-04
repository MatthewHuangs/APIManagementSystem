package com.ustc.huaweiapims.pojo.jsonconvert.swagger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:10
 */
public class Path {
    Map<String, Method> _map = new HashMap<>(); //string："/users"

    public Map<String, Method> get_map() {
        return _map;
    }

    public void set_map(Map<String, Method> _map) {
        this._map = _map;
    }
}
