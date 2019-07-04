package com.ustc.huaweiapims.pojo.jsonconvert.swagger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:09
 */
@JsonIgnoreProperties
public class Parameter {
    private String name = null;
    private String in = null;
    private String description = null;
    private boolean required = true;
    private String type = null;
    private String format = null;
    private double defaultIntType = 0.0;
    @JsonProperty("default")
    private String defaultStrType = null;

    public double getDefaultIntType() {
        return defaultIntType;
    }

    public void setDefaultIntType(double defaultIntType) {
        this.defaultIntType = defaultIntType;
    }

    public String getDefaultStrType() {
        return defaultStrType;
    }

    public void setDefaultStrType(String defaultStrType) {
        this.defaultStrType = defaultStrType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
