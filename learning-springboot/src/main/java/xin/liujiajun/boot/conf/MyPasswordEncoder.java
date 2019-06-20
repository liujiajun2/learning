package xin.liujiajun.boot.conf;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author LiuJiaJun
 * @date 2019/5/31 12:41
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
