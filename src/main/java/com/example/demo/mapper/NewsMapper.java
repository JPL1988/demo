package com.example.demo.mapper;

import com.example.demo.entity.News;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author false
 * @date 20/6/10 17:23
 */
@Repository
public interface NewsMapper {
    /**
     * 返回新闻列表
     * @return 新闻列表
     */
    List<News> findNews();

    /**
     * 通过id显示新闻
     * @param id
     * @return
     */
    News findById(int id);
}
