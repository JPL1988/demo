package com.example.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author false
 * @date 20/5/28 19:53
 */
@Controller
public class Index {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
