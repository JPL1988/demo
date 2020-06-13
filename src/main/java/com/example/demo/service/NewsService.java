package com.example.demo.service;

import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author false
 * @date 20/6/10 17:41
 */
@Service
public class NewsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NewsMapper newsMapper;

    public News findById(int id){
        return newsMapper.findById(id);
    }

    public List<News> findNews(){
        return newsMapper.findNews();
    }
}
