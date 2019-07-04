package com.ustc.huaweiapims.controller;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author matthew huang
 * @description
 * @date 2019/6/3 4:49 PM
 */
@Controller
public class CASController {

    @Value("${casClientLogoutUrl}")
    private String clientLogoutUrl;

    @RequestMapping("/index") // ({"/","/index"})
    public String toMainPage(ModelMap map) {
        map.addAttribute("name", "CAS_Client");
        return "index";
    }

    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request, HttpSession session) {
        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
        String loginUserName = principal.getName();
        session.setAttribute("loginUser", loginUserName);
        return "index";
    }

    @RequestMapping("/logout")
    public String toLogout(HttpSession session) {
        session.invalidate();//销毁session
        //使用cas退出成功后,跳转回主页面
        return "redirect:" + clientLogoutUrl;
    }

    @RequestMapping("/logoutsuccess")
    public String toLogoutSuccessPage() {
        return "redirect:index";
    }
}
