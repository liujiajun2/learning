package xin.liujiajun.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import xin.liujiajun.mybatis.base.MybatisUtil;
import xin.liujiajun.mybatis.mapper.PersonMapper;
import xin.liujiajun.mybatis.model.GenderEnum;
import xin.liujiajun.mybatis.model.Person;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author LiuJiaJun
 * @date 2018/9/27 12:15
 */
public class PersonTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //openSession 要设置为true 才会提交修改的数据
            sqlSession = MybatisUtil.getSqlSessionFactory().openSession(true);

            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

//            Person person1 = new Person();
//            person1.setName("李四");
//            List<String> list = Arrays.asList("游泳","健身","跑步");
//            person1.setInterest(list);
//            person1.setSex(GenderEnum.FEMALE);
//            person1.setCreateTime(Instant.now());
//            int add = personMapper.add(person1);
//            System.out.println(add);
//            System.out.println(person1.getId());

            Person person = personMapper.queryById(5);
            query(person);
        } finally {
            sqlSession.close();
        }

    }

    public static void query(Person person){
        //查询

        GenderEnum sex = person.getSex();
        System.out.println("code=" + sex.getCode() +"&"+ "value="+ sex.getValue());
        List<String> interest = person.getInterest();
        for (String str : interest) {
            System.out.println(str);
        }
        Instant createTime = person.getCreateTime();
        System.out.println(timeFormat(createTime));
        System.out.println(person);
    }
    public static String timeFormat(Instant instant){

        return LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
