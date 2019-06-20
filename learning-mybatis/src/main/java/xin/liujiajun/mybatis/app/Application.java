package xin.liujiajun.mybatis.app;


import org.apache.ibatis.session.SqlSession;
import xin.liujiajun.mybatis.base.MybatisUtil;
import xin.liujiajun.mybatis.mapper.StudentMapper;
import xin.liujiajun.mybatis.model.Student;

/**
 * @author LiuJiaJun
 * @date 2018/9/26 8:20
 */
public class Application {

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            Student student = studentMapper.queryById(1);
            System.out.println(student.getName());
        } finally {
            sqlSession.close();
        }
    }
}
