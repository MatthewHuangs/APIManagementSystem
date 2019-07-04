/*
 * 文件名： GenerateCode.java
 * 作者：胡晨茜
 * 功能：代码生成类
 * 修改日期：2019.6.11
 * copyright USTC
 */

package com.ustc.huaweiapims.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenerateCode {
    public void generateItfModel(String filename, String filepostfix)
    {
        String cmdstr = "cmd /c cd E:/000-EngineeringPractice/0527-swagger-codegen-cli && java -cp \"default-swagger-codegen-1.0.0.jar;swagger-codegen-cli-2.3.1.jar\" io.swagger.codegen.SwaggerCodegen generate -l default -o F:/swagger-demo-"+ filename + " -i "+ filename + "." + filepostfix;
        try
        {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmdstr);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<ERROR>");
            while ( (line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("</ERROR>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t)
        {
            t.printStackTrace();
        }
        System.out.println("Generated!");
    }


    public void createProjBackup(String urlProjSrc, String urlProjDst) throws IOException
    {
        CopyFile cfProj = new CopyFile();
        cfProj.copy(urlProjSrc, urlProjDst);
        System.out.println("Succeed!");
    }

    public void assignFiles(String urlApiSrc, String urlApiDst, String urlModelSrc, String urlModelDst) throws IOException
    {
        CopyFile cfFiles = new CopyFile();
        cfFiles.copy(urlApiSrc, urlApiDst);
        cfFiles.copy(urlModelSrc, urlModelDst);
        System.out.println("Succeed!");
    }

    public void packageFiles(String urlProjDst)
    {
        String cmdstr = "cmd /c cd " + urlProjDst + " && jar cvf api.jar -C src/ .";
        try
        {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmdstr);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<ERROR>");
            while ( (line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("</ERROR>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t)
        {
            t.printStackTrace();
        }
        System.out.println("Packaged!");
    }

    public void downLoadFile(String apiUrl, File out) {
        try{
            URL url = new URL(apiUrl);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(out);
            BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
            byte[] buffer = new byte[1024];
            int read = 0;
            while((read = in.read(buffer, 0, 1024)) >= 0)
                bout.write(buffer, 0, read);
            bout.close();
            in.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
