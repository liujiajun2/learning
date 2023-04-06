package xin.liujiajun.jodd.email;

import jodd.mail.Email;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-23 16:15
 **/
public class EmailDemo {

    public static void main(String[] args) {

        Email email = Email.create().from("18850023543@163.com")
                .to("761076426@qq.com")
                .subject("hello ")
                .textMessage("hello myself");

        

    }
}
