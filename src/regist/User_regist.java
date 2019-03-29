package regist;

import db.Connections;
import md5.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;

@WebServlet("/user_regist")
public class User_regist extends HttpServlet {
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
        System.out.println(connection);
        String sql = "insert into user(username,password,id,create_time) values (?,?,?,?)";
        String name = req.getParameter("uname");
        String id = req.getParameter("id");
        String password = MD5Util.encode(req.getParameter("upwd"));

        PreparedStatement preparedStatement = null;
        if (name!=null&&id!=null&&password!=null){
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, id);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = simpleDateFormat.format(new java.util.Date());
                preparedStatement.setString(4, time);
                preparedStatement.executeUpdate();
                connection.close();
                System.out.println("------------over");
            } catch (SQLIntegrityConstraintViolationException e) {
                out.println("<center>");
                out.println("<h1>ID已存在!</h1>");
                out.println("<br/>");
                out.println("<a href='regist.html'>重新注册</a>");
                out.println("</center>");
                return;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        out.println("<center>");
        out.println("<h1>注册完成！<h1>");
        out.println("<a href='index.html'>返回</a>");
        out.println("</center>");
    }
}
