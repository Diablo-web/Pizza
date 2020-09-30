package com.pizza404.controller;

import com.pizza404.model.Pizza;
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


@WebServlet(name = "EditController", urlPatterns = "/EditController")
public class EditController extends HttpServlet{
    
    private final String sql = "jdbc:derby://localhost:1527/pizza404";
    private final String name = "boot";
    private final String pass = "boot";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
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
            PreparedStatement pst = con.prepareStatement("select * from pizza where pizza_id="+pizza_id);
            ResultSet rst = pst.executeQuery();
            
            Pizza pi = new Pizza();
            if (rst.next()) {
                pi.setPizza_id(rst.getInt("pizza_id"));
                pi.setPizza_name(rst.getString("pizza_name"));
                pi.setDescription(rst.getString("description"));
                pi.setLink(rst.getString("link"));
                pi.setImglink(rst.getString("imglink"));
                pi.setQuantity(rst.getInt("quantity"));
                pi.setPrice(rst.getFloat("price"));
                
                req.setAttribute("pi", pi);
               //if(email.equals("admin@gmail.com")&&pass.equals("admin")){
                RequestDispatcher rd = req.getRequestDispatcher("addpizza.jsp");
                rd.forward(req, resp);
                //}else{
                  //  out.println("<script>"+"alert('Only Admin can asses this account. please login if your admin');"+"window.location='login.jsp';"+"</script>");
                //}
                
            }else{
                
                out.println("<script>"+"alert('Opps!!! Somthing whent wrong. please try again');"+"window.location='pizza.jsp';"+"</script>");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
