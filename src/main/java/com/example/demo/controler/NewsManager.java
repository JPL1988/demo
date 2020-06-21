package com.example.demo.controler;

import com.example.demo.entity.News;
import com.example.demo.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
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
        return "infoManage";
    }

    @RequestMapping("/modify")
    public String modify(Model model,@PathParam("newsId") int newsId){
        News news = newsService.findById(newsId);
        model.addAttribute("news",news);
        return "modify";
    }

    @RequestMapping("/addNews")
    public String addNews(){
        return "addNews";
    }

    @ResponseBody
    @RequestMapping("/delNews")
    public String delNews(@RequestParam("newsId")int newsId){
        logger.info("newsId:"+newsId);
        if(newsService.delById(newsId)){
            return "true";
        }
        return "false";
    }

    @ResponseBody
    @RequestMapping("/insertNews")
    public String insertNews(@RequestParam("title")String title,
                             @RequestParam("time")String time,
                             @RequestParam("source")String source,
                             @RequestParam("content")String content){
        News news = new News();
        news.setTitle(title);
        news.setTime(time);
        news.setSource(source);
        news.setArticle(content);
        logger.info(news.toString());
        if(newsService.insertNews(news)){
            return "true";
        }
        else{
            return "false";
        }
    }

    @ResponseBody
    @RequestMapping("/modifyNews")
    public String modifyNews(@RequestParam("title")String title,
                             @RequestParam("time")String time,
                             @RequestParam("source")String source,
                             @RequestParam("content")String content,
                             @RequestParam("newsId")int newsId){
        News news = new News();
        news.setTitle(title);
        news.setTime(time);
        news.setSource(source);
        news.setArticle(content);
        news.setId(newsId);
        logger.info(news.toString());
        if(newsService.modifyNews(news)){
            logger.info(newsService.findById(newsId).toString());
            return "true";
        }
        return "false";
    }
    @RequestMapping("/newsDetails")
    public String newsDetails(Model model,@PathParam("newsId") int newsId){
        News byId = newsService.findById(newsId);
        if(byId==null){
            return "404";
        }
        model.addAttribute("news",byId);
        return "newsDetails";
    }
}
