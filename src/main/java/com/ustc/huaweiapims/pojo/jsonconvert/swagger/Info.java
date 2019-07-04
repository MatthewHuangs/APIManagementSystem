package com.ustc.huaweiapims.pojo.jsonconvert.swagger;

/**
 * @author joker1937
 * @date 2019-05-2019/5/28 15:08
 */
public class Info {
    private Name contact = null; //Postman json不需要用到
    private String description = null;
    private String termsOfService = "www.baidu.com";
    private String title = null;
    private String version = "0.0.1";
    private License license = null;

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public Name getContact() {
        return contact;
    }

    public void setContact(Name contact) {
        this.contact = contact;
    }
}
