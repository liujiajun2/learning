package xin.liujiajun.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import xin.liujiajun.mybatis.model.Person;

/**
 * @author LiuJiaJun
 * @date 2018/9/27 11:42
 */
public interface PersonMapper {

    int add(Person person);

    Person queryById(@Param("id") Integer id);


}
