package xin.liujiajun.springmvc.myself.controller;

import xin.liujiajun.springmvc.myself.annotation.Controller;
import xin.liujiajun.springmvc.myself.annotation.RequestMapping;
import xin.liujiajun.springmvc.myself.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuJiaJun
 * @date 2019/6/29 11:24
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String test(HttpServletRequest request, HttpServletResponse response, @RequestParam String name) throws IOException {
        response.getWriter().write("receive from client name is : " + name);
        return "hello";
    }

    public static void main(String[] args) {
        String replace = "//dex".replaceAll("/+", "/");
        System.out.println(replace);
    }

}
