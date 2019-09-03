package xin.liujiajun.servlet.servlet;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liujiajun
 * @date 2019-08-26 19:12
 **/
@WebServlet(name = "counter",urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = getServletContext();

        Integer count = null;

        synchronized (context) {
            count =(Integer) context.getAttribute("counter");
            if (count == null) {
                count = 1;
            }else{
                count = count + 1;
            }
            context.setAttribute("counter",count);
        }
        resp.setContentType("text/html;charset=gb2312");
        PrintWriter out = resp.getWriter();
        out.println("<html><head>");
        out.println("<title>页面统计</title>");
        out.println("</head><body>");
        out.println("页面被访问" + "<b> " + count+ "</b>"  + "次");
        out.close();


    }
}
