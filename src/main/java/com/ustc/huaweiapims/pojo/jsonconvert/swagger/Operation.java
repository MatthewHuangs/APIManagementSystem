package com.ustc.huaweiapims.pojo.jsonconvert.swagger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:09
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Operation {
    private List<String> tags = null;
    private String summary = null;
    private String description = null;
    private String operationID = null;
    private List<String> consumes = null;
    private List<String> produces = null;
    private List<Parameter> parameters = null;
    private List<String> responses = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public Operation(List<String> tags, String summary, String description, String operationID, List<String> consumes, List<String> produces) {
        this.tags = tags;
        this.summary = summary;
        this.description = description;
        this.operationID = operationID;
        this.consumes = consumes;
        this.produces = produces;

    }

    public Operation(List<String> tags, String summary, String description, String operationID, List<String> consumes, List<String> produces, List<Parameter> parameters) {
        this.tags = tags;
        this.summary = summary;
        this.description = description;
        this.operationID = operationID;
        this.consumes = consumes;
        this.produces = produces;
        this.parameters = parameters;
    }

    public Operation() {}

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getOperationID() {
        return operationID;
    }

    public void setOperationID(String operationID) {
        this.operationID = operationID;
    }

    public List<String> getConsumes() {
        return consumes;
    }

    public void setConsumes(List<String> consumes) {
        this.consumes = consumes;
    }

    public List<String> getProduces() {
        return produces;
    }

    public void setProduces(List<String> produces) {
        this.produces = produces;
    }
}
