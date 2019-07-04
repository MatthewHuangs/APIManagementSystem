package com.ustc.huaweiapims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author matthew huang
 * @description
 * @date 2019/6/3 4:30 PM
 */
@Controller
@RequestMapping("/view")
public class ViewController {

//    浏览器（Webkit内核）的安全策略决定了file协议访问的应用无法使用XMLHttpRequest对象，firefox中允许
//    @RequestMapping("/swagger")
//    public String swagger() {
//        return "redirect:localhost:8889/swagger-ui.html";
//    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "view/welcome";
    }

    @RequestMapping("/curl")
    public String curl() { return "view/curl"; }

    @RequestMapping("/postman")
    public String postman(){
        return "view/postman";
    }

    @RequestMapping("/codegenerate")
    public String codegenerate() { return "view/codegenerate"; }

}
