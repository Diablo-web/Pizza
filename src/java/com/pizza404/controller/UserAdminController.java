package com.pizza404.controller;

import com.pizza404.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "UserAdminController", urlPatterns = "/UserAdminController")
public class UserAdminController extends HttpServlet{
    
    //@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        
        
        try {
            HttpSession session = req.getSession(false);
            
            if(session!=null){
                out.println("<script>"+"alert('Welcome Admin');"+"window.location='addpizza.jsp';"+"</script>");
            }else{
                out.println("<script>"+"alert('Some thing whent wrong');"+"window.location='pizza.jsp';"+"</script>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
