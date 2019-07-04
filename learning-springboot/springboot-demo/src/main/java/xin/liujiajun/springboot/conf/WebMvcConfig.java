package xin.liujiajun.springboot.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiuJiaJun
 * @date 2019/5/31 12:16
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws").setViewName("/socket");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/chat").setViewName("/chat");
    }
}
