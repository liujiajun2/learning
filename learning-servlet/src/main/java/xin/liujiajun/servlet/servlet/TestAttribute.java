package xin.liujiajun.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试request.getAttribute和request.getParameter的区别
 *
 * @author LiuJiaJun
 * @date 2018/10/18 12:56
 */
@WebServlet(name = "testAttribute",urlPatterns = {"/testAttribute"},loadOnStartup = 1)
public class TestAttribute extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/testAttribute.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        System.out.println("request.getAttribute(userName)" + req.getAttribute("userName"));
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        req.setAttribute("userName",userName);
        req.setAttribute("password",password);
        req.getRequestDispatcher("/view/testAttribute.jsp").forward(req,resp);
    }
}
