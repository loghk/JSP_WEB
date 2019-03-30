package login;

import db.Connections;
import md5.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user_login")
public class User_login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection connection = Connections.getConnection();

        String id = req.getParameter("id");
        String password = MD5Util.encode(req.getParameter("upwd"));

        String sql = "select * from user where id ='" + id + "'";
        System.out.println("----------" + sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String uname = resultSet.getString("username");
                String uid = resultSet.getString("id");
                String upwd = resultSet.getString("password");
                System.out.println(uname);
                System.out.println(uid);
                System.out.println(upwd);
                if (uid.equals(id) && upwd.equals(password)) {
                    System.out.println("----------------成功登陆");
                    HttpSession session = req.getSession();
                    session.setAttribute("logined", true);
                    this.getServletContext().setAttribute("uname", uname);
                    resp.sendRedirect("/welcome.jsp");
                } else {
                    out.println("<center>");
                    out.println("<h1>用户名或密码不正确!</h1>");
                    out.println("<br/>");
                    out.println("<a href='index.html'>重新登陆</a>");
                    out.println("</center>");
                }
            } else {
                out.println("<center>");
                out.println("<h1>用户名不存在!</h1>");
                out.println("<br/>");
                out.println("<a href='index.html'>重新登陆</a>");
                out.println("</center>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
