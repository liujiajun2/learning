package xin.liujiajun.mybatis.mapper;

import xin.liujiajun.mybatis.model.Student;

/**
 * @author LiuJiaJun
 * @date 2018/9/26 8:30
 */
public interface StudentMapper {

    Student queryById(Integer id);
}
