package com.ustc.huaweiapims.pojo.jsonconvert.postman;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:05
 */
public class Info {
    private String _postman_id = "6a892870-6883-4605-9f2c-ece244a294b0";
    private String name = null;
    private String description = null;
    private String schema = "https://schema.getpostman.com/json/collection/v2.1.0/collection.json";

    public Info(String _postman_id, String name, String description, String schema) {
        this._postman_id = _postman_id;
        this.name = name;
        this.description = description;
        this.schema = schema;
    }

    public String get_postman_id() {
        return _postman_id;
    }

    public void set_postman_id(String _postman_id) {
        this._postman_id = _postman_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
