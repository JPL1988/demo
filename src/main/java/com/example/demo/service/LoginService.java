package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.result.LoginResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author false
 * @date 20/5/29 14:04
 */
@Service
public class LoginService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HttpServletRequest httpServletRequest;
    /**
     * md5加密字符串
     */
    public static final String MD_5 ="fploa13./JOKFNpofIODt";
    /**
     * mapper映射
     */
    @Autowired
    UserMapper userMapper;

    public LoginResult loginResult(String name,String pwd,boolean flag,HttpServletResponse response){
        logger.info("user:-----------"+name);
        logger.info("pwd:-----------"+pwd);
        logger.info("record:-----------"+flag);
        //查询数据库
        User user1 = userMapper.findUser(name,pwd);
        if(user1==null){
            return new LoginResult(false,"","用户名或密码错误");
        }else {
            //写入session
            if(flag==true){
                String token = getMd5(name);
                httpServletRequest.getSession().setAttribute(token,name);
                //设置session过期时间30天
                httpServletRequest.getSession().setMaxInactiveInterval(30*24*60*60);
                System.out.println("session过期时间 : "+httpServletRequest.getSession().getMaxInactiveInterval());
                System.out.println("session token: "+httpServletRequest.getSession().getAttribute(token));
                Cookie cookie = new Cookie("token",token);
                cookie.setMaxAge(30*24*60*60);
                response.addCookie(cookie);
            }
            return new LoginResult(true,name,"");
        }
    }
    public LoginResult verifyLogin(){
        Cookie[] cookies = httpServletRequest.getCookies();
        String token="";
        for (Cookie cookie:cookies){
            if("token".equals(cookie.getName())){
                token=cookie.getValue();
                break;
            }
        }
        logger.info("token:"+token);
        if(token==null||token.length()==0){
            return new LoginResult(false,"","error");
        }
        String name =(String) httpServletRequest.getSession().getAttribute(token);
        logger.info("name:"+name);
        if(name==null||name.length()==0){
            return new LoginResult(false,"","error");
        }
        return new LoginResult(true,name,"");
    }
    private String getMd5(String name){
        return DigestUtils.md5DigestAsHex((name+MD_5).getBytes());
    }
}
