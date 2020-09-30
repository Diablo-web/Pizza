package com.pizza404.controller;

import com.pizza404.model.Pizza;
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



@WebServlet(name = "UpdateController", urlPatterns = "/UpdateController")
public class UpdateController extends HttpServlet{
    
    private final String sql = "jdbc:derby://localhost:1527/pizza404";
    private final String name = "boot";
    private final String pass = "boot";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        Pizza pi = new Pizza();
        pi.setPizza_id(Integer.parseInt(req.getParameter("pizza_id")));
        pi.setPizza_name(req.getParameter("pizza_name"));
        pi.setDescription(req.getParameter("description"));
        pi.setLink(req.getParameter("link"));
        pi.setImglink(req.getParameter("imglink"));
        pi.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        pi.setPrice(Float.parseFloat(req.getParameter("price")));
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(sql,name,pass);
            PreparedStatement pst = con.prepareStatement("update pizza set pizza_name=? , description=? , link=? ,imglink=? , quantity=? , price=? where pizza_id=?");
            
            pst.setString(1, pi.getPizza_name());
            pst.setString(2, pi.getDescription());
            pst.setString(3, pi.getLink());
            pst.setString(4, pi.getImglink());
            pst.setInt(5, pi.getQuantity());
            pst.setFloat(6, pi.getPrice());
            pst.setInt(7, pi.getPizza_id());
            pst.executeUpdate();
            
            resp.sendRedirect("pizza.jsp");
            //PrintWriter out = resp.getWriter();
            //out.println("<script>"+"alert('Updated successfully');"+"window.location='pizza.jsp';"+"</script>");
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
