/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Admin;

import com.Database.AdminDatabase;
import com.Database.FormBeans;
import com.Database.PatientDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ramakrishna
 */
public class AcceptRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
        boolean isAccept=new AdminDatabase().Accept(id,userName);
        String path="";
        if(isAccept){
              ArrayList<FormBeans> requests=new PatientDatabase().patientRequests(userName,"admin");
        
        if(!requests.isEmpty()){
            request.setAttribute("requests", requests);
            path="./viewRequests.jsp";
     
        }
        }else{
              ArrayList<FormBeans> requests=new PatientDatabase().patientRequests(userName,"admin");
       
        if(!requests.isEmpty()){
            request.setAttribute("requests", requests);
            path="./viewRequests.jsp";
     
        }
        }
        RequestDispatcher dis=request.getRequestDispatcher(path);
        dis.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
