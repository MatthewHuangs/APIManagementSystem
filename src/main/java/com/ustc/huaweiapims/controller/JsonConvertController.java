package com.ustc.huaweiapims.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ustc.huaweiapims.pojo.jsonconvert.postman.*;
import com.ustc.huaweiapims.pojo.jsonconvert.swagger.Info;
import com.ustc.huaweiapims.pojo.jsonconvert.swagger.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/")
public class JsonConvertController {

    @RequestMapping(value = "/jsonconvert",method = RequestMethod.POST)
    @ResponseBody
    //@Test
    public String serializeToPostmanJson(@RequestParam String jsonStr) throws IOException {
        JsonConvertController jsonConvertController = new JsonConvertController();
        Postman postman = new Postman();
        Swagger swagger = jsonConvertController.DeserializeSwaggerJson(jsonStr);
        com.ustc.huaweiapims.pojo.jsonconvert.postman.Info postmanInfo = new com.ustc.huaweiapims.pojo.jsonconvert.postman.Info("4c231ca1-9fdd-41d9-afb4-3c3a947eb320", swagger.getInfo().getTitle(),
                swagger.getInfo().getDescription(), "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        postman.setInfo(postmanInfo);


        int size = swagger.getTags().size();
        List<API> items = new ArrayList<>();
        API api = new API();
        for (int i = 0; i < size; ++i) {
            api = new API();
            String apiName = swagger.getTags().get(i).getName();
            String s = apiName.substring(0, apiName.length()-11) + "s";
            api.setName(s);

            Set<Map.Entry<String, Method>> entrySet = swagger.getPaths().get_map().entrySet();
            Iterator<Map.Entry<String, Method>> it = entrySet.iterator();
            List<PostmanOperation> postmanListOperation = new ArrayList<>();
            Operation operation = new Operation();
            while (it.hasNext()) {
                //获取每一个Entry对象
                Map.Entry<String, Method> entry = it.next();
                String key = entry.getKey(); // "/users"
                Method method = entry.getValue();

                Set<Map.Entry<String, Operation>> methodEntrySet = method.get_map().entrySet();
                Iterator<Map.Entry<String, Operation>> it1 = methodEntrySet.iterator();
                while (it1.hasNext()) {
                    Map.Entry<String, Operation> entry1 = it1.next();
                    String key1 = entry1.getKey(); // "get" / "post"
                    operation = entry1.getValue();

                    if(operation.getTags().get(0).equals(apiName)) {
                        PostmanOperation postmanOperation = new PostmanOperation();

                        postmanOperation.setName(operation.getSummary());

                        Request request = new Request();
                        request.setMethod(key1.toUpperCase());

                        KeyValue keyValue1 = new KeyValue("Accept", "*/*");
                        KeyValue keyValue2 = new KeyValue("ContentType", "application/json");
                        List<KeyValue> header = new ArrayList<>();
                        header.add(keyValue1);
                        header.add(keyValue2);
                        request.setHeader(header);

                        Body body = new Body();
                        body.setMode("raw");
                        body.setRaw("");
                        request.setBody(body);

                        URL url = new URL();

                        StringBuilder stringBuilder = new StringBuilder(key);
                        if(key.split("/").length > 2) {
                            stringBuilder = new StringBuilder(key.substring(0, key.length()-1));
                            int index = stringBuilder.indexOf("{");
                            stringBuilder.setCharAt(index, ':');
                        }

                        url.setRaw("http://" + swagger.getHost() + stringBuilder);

                        List<String> host = new ArrayList<>();
                        String hostString = swagger.getHost();
                        for(String idx : hostString.substring(0, hostString.length()-5).split("\\.", 3)) {
                            host.add(idx);
                        }
                        url.setHost(host);

                        url.setProtocol("http");

                        url.setPort(swagger.getHost().substring(swagger.getHost().length()-4));

                        List<String> path = new ArrayList<>();
                        for(String idx : stringBuilder.toString().split("/")) {
                            if(idx.length() > 0)
                                path.add(idx);
                        }
                        url.setPath(path);
                        request.setUrl(url);

                        try {
                            List<KeyValue> queryKeyValues = new ArrayList<>();
                            List<KeyValue> variableKeyValues = new ArrayList<>();
                            for(int j = 0; j < operation.getParameters().size(); ++j) {
                                if(operation.getParameters().get(j).getIn().equals("query")) {
                                    if(key1.equals("put")) {
                                        KeyValue keyValue = new KeyValue(operation.getParameters().get(j).getName(), operation.getParameters().get(j).getDefaultStrType());
                                        queryKeyValues.add(keyValue);
                                        url.setQuery(queryKeyValues);

                                        if(j == 1) url.setRaw(url.getRaw() + "?");
                                        System.out.println(url.getRaw());
                                        if(j != operation.getParameters().size()-1) {
                                            url.setRaw(url.getRaw() + operation.getParameters().get(j).getName() + "=" + operation.getParameters().get(j).getDefaultStrType() + "&");
                                        }
                                        else url.setRaw(url.getRaw() + operation.getParameters().get(j).getName() + "=" + operation.getParameters().get(j).getDefaultStrType());
                                    } else if(key1.equals("post")) {
                                        KeyValue keyValue = new KeyValue(operation.getParameters().get(j).getName(), "{{" + operation.getParameters().get(j).getName() + "}}");
                                        queryKeyValues.add(keyValue);
                                        url.setQuery(queryKeyValues);

                                        if(j == 0) url.setRaw(url.getRaw() + "?");
                                        if(j != operation.getParameters().size()-1) {
                                            url.setRaw(url.getRaw() + operation.getParameters().get(j).getName() + "={{" + operation.getParameters().get(j).getName() + "}}&");
                                        }
                                        else url.setRaw(url.getRaw() + operation.getParameters().get(j).getName() + "={{" + operation.getParameters().get(j).getName() + "}}");
                                    }
                                } else if(operation.getParameters().get(j).getIn().equals("path")) {
                                    KeyValue keyValue = new KeyValue(operation.getParameters().get(j).getName(), "{{" + operation.getParameters().get(j).getName() + "}}");
                                    variableKeyValues.add(keyValue);
                                    url.setVariable(variableKeyValues);
                                }
                            }
                        } catch (Exception e) {

                        }


                        request.setDescription(operation.getDescription());

                        postmanOperation.setRequest(request);
                        postmanOperation.setResponse(new ArrayList<>());

                        postmanListOperation.add(postmanOperation);

                    }

                }
                api.setItem(postmanListOperation);
            }

            api.setDescription("Folders for " + s);
            items.add(api);
        }


        postman.setItem(items);

        // 过滤器（属性值为空时不参加序列化）
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                if(o1 == null) {
                    return true;
                }
                return false;
            }
        });

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String postmanJsonStr = objectMapper.writeValueAsString(postman);

        FileWriter fw = new FileWriter("/Users/Desktop/postman.json");
        BufferedWriter out = new BufferedWriter(fw);
        out.write(postmanJsonStr);
        out.close();

        return postmanJsonStr;
    }




    public Swagger DeserializeSwaggerJson(String inputJson) throws IOException {

        FileOutputStream fos = new FileOutputStream("/Users/Desktop/input.json");
        fos.write(inputJson.getBytes());
        fos.close();
        File jsonFile = new File("/Users/Desktop/input.json");
        String content = FileUtils.readFileToString(jsonFile);
        JSONObject jsonObject = JSONObject.fromObject(content);
        ObjectMapper objectMapper = new ObjectMapper();
        Swagger jsonSwagger = new Swagger();

        //解析swagger
        jsonSwagger.setSwagger(jsonObject.getString("swagger"));

        //解析info
        String info = jsonObject.getString("info");
        Info info1 = objectMapper.readValue(info, Info.class);
        jsonSwagger.setInfo(info1);

        //解析host
        String host = jsonObject.getString("host");
        jsonSwagger.setHost(host);

        //解析basePath
        String basePath = jsonObject.getString("basePath");
        jsonSwagger.setBasePath(basePath);

        //解析tags
        JSONArray jsonTags = jsonObject.getJSONArray("tags");
        List<Tag> tags = new ArrayList<>();
        for(int i = 0; i < jsonTags.size(); ++i) {
            Tag tag = new Tag();
            tag.setName(jsonTags.getJSONObject(i).getString("name"));
            tag.setDescription(jsonTags.getJSONObject(i).getString("description"));
            tags.add(tag);
        }
        jsonSwagger.setTags(tags);

        //解析paths
        Path paths = new Path();
        String pathsString = jsonObject.getString("paths");
        JSONObject user = JSONObject.fromObject(pathsString);
        Iterator<String> iterator = user.keys();
        while(iterator.hasNext()) {
            String key = iterator.next();
            //String value = user.getString(key);
            JSONObject obj = user.getJSONObject(key);
            Iterator<String> iterator1 = obj.keys();
            Method method = new Method();
            while(iterator1.hasNext()) {
                String key1 = iterator1.next();
                //String value1 = obj.getString(key1);
                JSONObject methodObj = obj.getJSONObject(key1);
                Operation operation = new Operation();
                switch (key1) {
                    case "get":     operation = deserializeGetMethod(methodObj);    break;
                    case "post":    operation = deserializePostMethod(methodObj);   break;
                    case "put":     operation = deserializePutMethod(methodObj);    break;
                    case "delete":  operation = deserializeDeleteMethod(methodObj); break;
                }

                Map<String, Operation> map = new HashMap<>();
                method.get_map().put(key1, operation);
            }

            Map<String, Method> map1 = new HashMap<>();
            paths.get_map().put(key, method);
        }

        jsonSwagger.setPaths(paths);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonStr = objectMapper.writeValueAsString(jsonSwagger);
        //System.out.println(jsonStr);

        return jsonSwagger;
    }


    /**
     * @function: deserializeGetMethod
     * @description: 解析"get"字段
     * @author: Joker1937
     **/
    public Operation deserializeGetMethod(JSONObject getMethod) throws IOException {
        // 解析Operation中的tags
        List<String> methodTags = new ArrayList<>();
        JSONArray jsonArrayTags = getMethod.getJSONArray("tags");
        for(int i = 0; i < jsonArrayTags.size(); ++i) {
            methodTags.add(jsonArrayTags.getString(i));
        }

        //解析Operation中的summary
        String methodSummary = getMethod.getString("summary");
        //System.out.println(summary);

        //解析Operation中的description
        String methodDescription = null;
        try {
            methodDescription = getMethod.getString("description");
        } catch (Exception e) {

        }

        //解析Operation中的operationId
        String methodOperationId = getMethod.getString("operationId");

        //解析Operation中的consumes
        List<String> methodConsumes = new ArrayList<>();
        JSONArray jsonArrayConsumes = getMethod.getJSONArray("consumes");
        for(int i = 0; i < jsonArrayConsumes.size(); ++i) {
            methodConsumes.add(jsonArrayConsumes.getString(i));
        }

        //解析Operation中的produces
        List<String> methodProduces = new ArrayList<>();
        JSONArray jsonArrayProduces = getMethod.getJSONArray("produces");
        for(int i = 0; i < jsonArrayProduces.size(); ++i) {
            methodProduces.add(jsonArrayProduces.getString(i));
        }

        try {
            //解析Operation中的parameters
            if (getMethod.getJSONArray("parameters") != null) {
                Parameter parameter = new Parameter();
                ObjectMapper objectMapper = new ObjectMapper();
                List<Parameter> methodParameters = new ArrayList<>();
                JSONArray jsonArrayParameters = getMethod.getJSONArray("parameters");
                for (int i = 0; i < jsonArrayParameters.size(); ++i) {
                    JSONObject jsonObject = jsonArrayParameters.getJSONObject(i);
                    String s = jsonObject.toString();
                    parameter = objectMapper.readValue(s, Parameter.class);
                    methodParameters.add(parameter);
                }
                //response不需要取
                return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                        methodConsumes, methodProduces, methodParameters);
            }
        } catch (Exception e){

        }

        return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                methodConsumes, methodProduces);

    }

    /**
     * @function: deserializePostMethod
     * @description: 解析"post"字段
     * @author: Joker1937
     **/
    public Operation deserializePostMethod(JSONObject postMethod) throws IOException {
        // 解析Operation中的tags
        List<String> methodTags = new ArrayList<>();
        JSONArray jsonArrayTags = postMethod.getJSONArray("tags");
        for(int i = 0; i < jsonArrayTags.size(); ++i) {
            methodTags.add(jsonArrayTags.getString(i));
        }

        //解析Operation中的summary
        String methodSummary = postMethod.getString("summary");
        //System.out.println(summary);

        //解析Operation中的description
        String methodDescription = null;
        try {
            methodDescription = postMethod.getString("description");
        } catch (Exception e) {

        }


        //解析Operation中的operationId
        String methodOperationId = postMethod.getString("operationId");

        //解析Operation中的consumes
        List<String> methodConsumes = new ArrayList<>();
        JSONArray jsonArrayConsumes = postMethod.getJSONArray("consumes");
        for(int i = 0; i < jsonArrayConsumes.size(); ++i) {
            methodConsumes.add(jsonArrayConsumes.getString(i));
        }

        //解析Operation中的produces
        List<String> methodProduces = new ArrayList<>();
        JSONArray jsonArrayProduces = postMethod.getJSONArray("produces");
        for(int i = 0; i < jsonArrayProduces.size(); ++i) {
            methodProduces.add(jsonArrayProduces.getString(i));
        }

        //解析Operation中的parameters
        try {
            if (postMethod.getJSONArray("parameters") != null) {
                Parameter parameter = new Parameter();
                ObjectMapper objectMapper = new ObjectMapper();
                List<Parameter> methodParameters = new ArrayList<>();
                JSONArray jsonArrayParameters = postMethod.getJSONArray("parameters");
                for (int i = 0; i < jsonArrayParameters.size(); ++i) {
                    JSONObject jsonObject = jsonArrayParameters.getJSONObject(i);
                    String s = jsonObject.toString();
                    parameter = objectMapper.readValue(s, Parameter.class);

                    methodParameters.add(parameter);
                }


                return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                        methodConsumes, methodProduces, methodParameters);
            }
        } catch (Exception e) {

        }

        return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                methodConsumes, methodProduces);
    }

    /**
     * @function: deserializePutMethod
     * @description: 解析"put"字段
     * @author: Joker1937
     **/
    public Operation deserializePutMethod(JSONObject putMethod) throws IOException {
        // 解析Operation中的tags
        List<String> methodTags = new ArrayList<>();
        JSONArray jsonArrayTags = putMethod.getJSONArray("tags");
        for(int i = 0; i < jsonArrayTags.size(); ++i) {
            methodTags.add(jsonArrayTags.getString(i));
        }

        //解析Operation中的summary
        String methodSummary = putMethod.getString("summary");
        //System.out.println(summary);

        //解析Operation中的description
        String methodDescription = null;
        try {
            methodDescription = putMethod.getString("description");
        } catch (Exception e) {

        }

        //解析Operation中的operationId
        String methodOperationId = putMethod.getString("operationId");

        //解析Operation中的consumes
        List<String> methodConsumes = new ArrayList<>();
        JSONArray jsonArrayConsumes = putMethod.getJSONArray("consumes");
        for(int i = 0; i < jsonArrayConsumes.size(); ++i) {
            methodConsumes.add(jsonArrayConsumes.getString(i));
        }

        //解析Operation中的consumes
        List<String> methodProduces = new ArrayList<>();
        JSONArray jsonArrayProduces = putMethod.getJSONArray("produces");
        for(int i = 0; i < jsonArrayProduces.size(); ++i) {
            methodProduces.add(jsonArrayProduces.getString(i));
        }

        //解析Operation中的parameters
        try {
            if (putMethod.getJSONArray("parameters") != null) {
                Parameter parameter = new Parameter();
                ObjectMapper objectMapper = new ObjectMapper();
                List<Parameter> methodParameters = new ArrayList<>();
                JSONArray jsonArrayParameters = putMethod.getJSONArray("parameters");
                for (int i = 0; i < jsonArrayParameters.size(); ++i) {
                    JSONObject jsonObject = jsonArrayParameters.getJSONObject(i);
                    String s = jsonObject.toString();
                    System.out.println(s);
                    parameter = objectMapper.readValue(s, Parameter.class);
                    methodParameters.add(parameter);
                }
                //response不需要取
                return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                        methodConsumes, methodProduces, methodParameters);
            }
        } catch (Exception e) {

        }

        return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                methodConsumes, methodProduces);
    }

    /**
     * @function: deserializeDeleteMethod
     * @description: 解析"delete"字段
     * @author: Joker1937
     **/
    public Operation deserializeDeleteMethod(JSONObject deleteMethod) throws IOException {
        // 解析Operation中的tags
        List<String> methodTags = new ArrayList<>();
        JSONArray jsonArrayTags = deleteMethod.getJSONArray("tags");
        for(int i = 0; i < jsonArrayTags.size(); ++i) {
            methodTags.add(jsonArrayTags.getString(i));
        }

        //解析Operation中的summary
        String methodSummary = deleteMethod.getString("summary");
        //System.out.println(summary);

        //解析Operation中的description
        String methodDescription = null;
        try {
            methodDescription = deleteMethod.getString("description");
        } catch (Exception e) {

        }

        //解析Operation中的operationId
        String methodOperationId = deleteMethod.getString("operationId");

        //解析Operation中的consumes
        List<String> methodConsumes = new ArrayList<>();
        JSONArray jsonArrayConsumes = deleteMethod.getJSONArray("consumes");
        for(int i = 0; i < jsonArrayConsumes.size(); ++i) {
            methodConsumes.add(jsonArrayConsumes.getString(i));
        }

        //解析Operation中的produces
        List<String> methodProduces = new ArrayList<>();
        JSONArray jsonArrayProduces = deleteMethod.getJSONArray("produces");
        for(int i = 0; i < jsonArrayProduces.size(); ++i) {
            methodProduces.add(jsonArrayProduces.getString(i));
        }

        //解析Operation中的parameters
        try {
            if(deleteMethod.getJSONArray("parameters") != null) {
                Parameter parameter = new Parameter();
                ObjectMapper objectMapper = new ObjectMapper();
                List<Parameter> methodParameters = new ArrayList<>();
                JSONArray jsonArrayParameters = deleteMethod.getJSONArray("parameters");
                for(int i = 0; i < jsonArrayParameters.size(); ++i) {
                    JSONObject jsonObject = jsonArrayParameters.getJSONObject(i);
                    String s = jsonObject.toString();
                    parameter = objectMapper.readValue(s, Parameter.class);
                    methodParameters.add(parameter);
                }

                //response不需要取
                return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                        methodConsumes, methodProduces, methodParameters);
            }
        } catch (Exception e) {

        }

        return new Operation(methodTags, methodSummary, methodDescription, methodOperationId,
                methodConsumes, methodProduces);
    }
}
