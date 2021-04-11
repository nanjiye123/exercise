package com.exercise.testspringboot.mapper;

import com.exercise.testspringboot.domain.User;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper {

    @Insert("insert into user(name,phone,create_time,age) values(#{name},#{phone}," +
            "#{createTime},#{age})")
    int insert(User user);

}
