package xin.liujiajun.mybatis.base;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

/**
 * @author LiuJiaJun
 * @date 2018/9/26 8:31
 */
public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory = null;

    private final static Class myClass = MybatisUtil.class;

    private MybatisUtil() {
    }


    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
                synchronized (myClass) {
                    if (sqlSessionFactory == null) {
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }
        return sqlSessionFactory;

    }
}
