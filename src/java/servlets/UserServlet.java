/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author nuket
 */
public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       UserService us = new UserService();
       String action =  (String) request.getParameter("action");
       request.setAttribute("action",action);
       String form = "";
       
       
       String email = (String) request.getParameter("email");
       String firstName =(String) request.getParameter("first");
       String lastName = (String) request.getParameter("last");
       String role = (String) request.getParameter("role");
       
       request.setAttribute("role", role);
       request.setAttribute("first",firstName);
       request.setAttribute("last",lastName);
       request.setAttribute("email",email);
       
       if(action == null||action.equals("")){
           form = "add";
       }else if(action.equals("edit")){
           User user = null;
           try{
              user = us.getUser(firstName);
           } catch (Exception ex) {
               Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
           request.setAttribute("user",user);
           form ="edit";
       }else if(action.equals("toEdit")){
           
       }else if(action.equals("delete")){
           try {
               us.delete(email);
               form="add";
           } catch (Exception ex) {
               Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       request.setAttribute("form",form);
        
       try {
           HttpSession session = request.getSession();
           List<User> users = us.getAll();
           request.setAttribute("users",users);
       } catch(Exception ex){
           Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
       }
       
       
       
       getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        String email = (String) request.getParameter("email");
       String firstName =(String) request.getParameter("first");
       String lastName = (String) request.getParameter("last");
       String password = (String) request.getParameter("password");
       int role =  Integer.parseInt(request.getParameter("role"));
       String action = request.getParameter("action");
       if(action.equals("add")){
            try {
                us.insert(email, firstName, lastName, password, role);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       
       try {
           HttpSession session = request.getSession();
           List<User> users = us.getAll();
           request.setAttribute("users",users);
       } catch(Exception ex){
           Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
       }
       
       
       
       getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
    
 
}
