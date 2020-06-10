package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author false
 * @date 20/5/29 13:19
 */

@Repository
public interface UserMapper {
    /**
     * 查找user验证密码
     * @param name username
     * @param pwd  password
     * @return user
     */
    User findUser(@Param("name") String name,@Param("pwd") String pwd);

    /**
     * 查找用户
     * @param id
     * @return
     */
    User findById(int id);
}
