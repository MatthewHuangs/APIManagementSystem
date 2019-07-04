package com.ustc.huaweiapims.utils;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GeneratecodeApplication {

    public static void main(String[] args) throws IOException {
        GenerateCode gc = new GenerateCode();
        //0.保存url的json文件
        //gc.downLoadFile("http://petstore.swagger.io/v2/swagger.json", new File("E:\\000-EngineeringPractice\\0527-swagger-codegen-cli\\petstore_download.json"));

        //1.利用自定义模板和swagger-codegen，依据输入的json/yaml文件，生成接口及model代码文件
        String filename = "api";
        String filepostfix = "json";

        gc.generateItfModel(filename, filepostfix);

        //2.创建一个供下载的项目备份
        String urlProjSrc = "E:/000-EngineeringPractice/generated-api-files/API";
        String urlProjDst = "E:/000-EngineeringPractice/generated-api-files-"+ filename + "/API";
        gc.createProjBackup(urlProjSrc, urlProjDst);

        //3.将生成的文件复制到项目备份的指定路径下
        // API接口源文件夹
        String urlApiSrc = "F:/swagger-demo-"+ filename + "/src/com/rest/API";
        // API接口目标文件夹
        String urlApiDst = urlProjDst + "/src/main/java/com/rest/API";
        // model源文件夹
        String urlModelSrc = "F:/swagger-demo-"+ filename + "/src/com/rest/model";
        // model目标文件夹
        String urlModelDst = urlProjDst + "/src/main/java/com/rest/model";
        gc.assignFiles(urlApiSrc, urlApiDst, urlModelSrc, urlModelDst);

        //4.将备份项目打包，供用户下载
        gc.packageFiles(urlProjDst);

    }

}
