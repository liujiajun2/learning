package xin.liujiajun.springboot.test.sentinel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liujiajun
 * @date 2019-09-24 15:41
 **/
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
