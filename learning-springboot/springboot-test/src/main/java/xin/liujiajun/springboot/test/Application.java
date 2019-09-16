package xin.liujiajun.springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liujiajun
 * @date 2019-09-06 15:49
 **/
@SpringBootApplication
@RestController
public class Application {

    private String filename = "test.cfg";

    @GetMapping("/test.cfg")
    public void test(HttpServletResponse response) {
        String cfgVersion = "#!version:1.0.0.1";
        String newLine = "\r\n";

        StringBuffer sb = new StringBuffer(cfgVersion).append(newLine);
        sb.append("ldap.enable = ").append("1").append(newLine);
        String content = sb.toString();
        try {
            writeHeader(response);

            output(response, content);
        } catch (IOException e) {

            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void writeHeader(HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=\""
                + this.filename + "\"");
        response.setHeader("Accept-Range", "bytes");
        response.setHeader("Expires", "-1");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", -1);

    }

    public void output(HttpServletResponse response, String content) throws IOException {
        response.getOutputStream().write(content.getBytes());
        response.getOutputStream().flush();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
