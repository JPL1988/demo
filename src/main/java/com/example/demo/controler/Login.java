package com.example.demo.controler;

import com.example.demo.result.LoginResult;
import com.example.demo.service.LoginService;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Enumeration;

/**
 * @author false
 * @date 20/5/29 10:10
 */
@Controller
public class Login {
    @Autowired
    LoginService loginService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/login")
    @ResponseBody
    public String login(Model model,
                        @RequestParam("user") String user,
                        @RequestParam("pwd") String pwd,
                        @RequestParam("flag")boolean record,
                        HttpServletResponse response){
        if(user==null||pwd==null){
            return JSONObject.fromObject(new LoginResult(false,"","")).toString();
        }
        LoginResult result = loginService.loginResult(user,pwd,record,response);
        JSONObject json = JSONObject.fromObject(result);
        System.out.println("json:______"+json.toString());
        return json.toString();
    }

    @ResponseBody
    @RequestMapping("/verify")
    public String verifyLogin(Model model){
        LoginResult result = loginService.verifyLogin();
        return JSONObject.fromObject(result).toString();
    }

    @RequestMapping("/admin")
    public String Admin(Model model,
                        @RequestParam("user") String name){
        logger.error("name:"+name);
        model.addAttribute("user",name);
        return "admin";
    }
    @ResponseBody
    @RequestMapping("/person")
    public String personCount(HttpServletRequest httpServletRequest){
        Enumeration<String> attributeNames = httpServletRequest.getSession().getAttributeNames();
        int count=0;
        for (;attributeNames.hasMoreElements();){
            attributeNames.nextElement();
            count++;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count",count);
        logger.info(jsonObject.toString());
        return jsonObject.toString();
    }
}
