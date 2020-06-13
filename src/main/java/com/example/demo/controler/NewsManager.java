package com.example.demo.controler;

import com.example.demo.entity.News;
import com.example.demo.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author false
 * @date 20/6/12 14:12
 */
@Controller
public class NewsManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NewsService newsService;
    @RequestMapping("/logo")
    public String logo(){
        logger.info("logo_______________________");
        return "index";
    }
    @RequestMapping("/newsManage")
    public String newsManage(Model model){
        List<News> news = newsService.findNews();
        //填充数据到model中
        model.addAttribute("news",news);
        return "newsManage";
    }

    @RequestMapping("/infoManage")
    public String infoManage(){
        logger.info("infoManage_______________________");
        return "infoManage";
    }
}
