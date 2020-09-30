    package com.pizza404.controller;

import com.pizza404.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(name = "LoginController", urlPatterns = "/LoginController")
public class LoginController extends HttpServlet{
    
    private String sql = "jdbc:derby://localhost:1527/pizza404";
    private String name = "boot";
    private String pass = "boot";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    
        User user = new User();
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
       
        String email = user.getEmail();
        String userpass = user.getPassword();
        
        PrintWriter out = resp.getWriter();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(sql,name,pass);
            PreparedStatement pst = con.prepareStatement("select * from users where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, userpass);
            ResultSet rs = pst.executeQuery();
            
                if(rs.next()){
                    HttpSession session = req.getSession();
                    if(email.equals("admin@gmail.com") && userpass.equals("admin")){
                        
                        session.setAttribute("user", user);
                        
                       out.println("<script>"+"alert('Welcome Admin');"+"window.location='addpizza.jsp';"+"</script>");
                       // resp.sendRedirect("addpizza.jsp");
                    }else{
                        //session.setAttribute("user", user);
                        out.println(""+"<script>"+"alert('Welcome ');"+"window.location='pizza.jsp';"+"</script>"+"");
                    
                    //resp.sendRedirect("login.jsp");            
                    }
                }else{
                    out.println(""+"<script>alert('Failed to login email or password invalid');"+"window.location='login.jsp';</script>"+"");
                }
        } catch (Exception e) {
            System.out.print(e);
        }
        }
        
}

