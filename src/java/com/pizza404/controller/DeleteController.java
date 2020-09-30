package com.pizza404.controller;

import com.pizza404.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "DeleteController", urlPatterns = "/DeleteController")
public class DeleteController extends HttpServlet{
    private final String sql = "jdbc:derby://localhost:1527/pizza404";
    private final String name = "boot";
    private final String pass = "boot";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pizza_id = Integer.parseInt(req.getParameter("pizza_id"));
        
        User user = new User();
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
       
        String email = user.getEmail();
        String userpass = user.getPassword();
        
        PrintWriter out = resp.getWriter();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(sql,name,pass);
            
            if(email.equals("admin@gmail.com") && userpass.equals("admin")){
            PreparedStatement pst = con.prepareStatement("update pizza set stock='D' where pizza_id="+pizza_id);
            
            pst.executeUpdate();
            
            
            out.println(""+"<script>"+"alert('Deleted sucessfully');"+"window.location='pizza.jsp';"+"</script>");
            }else{
                        //session.setAttribute("user", user);
                        out.println(""+"<script>"+"alert('You must be a admin to delete');"+"window.location='pizza.jsp';"+"</script>"+"");
                    
                    //resp.sendRedirect("login.jsp");            
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
