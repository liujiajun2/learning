package xin.liujiajun.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author LiuJiaJun
 * @date 2019/5/30 13:35
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model){
        String uid = UUID.randomUUID().toString().replace("-", "");
        model.addAttribute("uid", uid);
        return "index";
    }
    @GetMapping("socket")
    public String socket(){
        return "socket";
    }
    @GetMapping("/index/json")
    @ResponseBody
    public String json(){
        return "hello";
    }
}
