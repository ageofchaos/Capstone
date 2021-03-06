/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import core.AdminFunctions;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebaslab
 */
@WebServlet(name = "AdminOperations", urlPatterns = {"/AdminOperations"})
public class AdminOperations extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * This servlet is used to change the locked status of a user, to delete a user, and to reset a user's password.
     * It receives parameters from the admin.jsp page letting it know whether or not to perform a certain action.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String locked = request.getParameter("locked");
        String delete = request.getParameter("delete");
        String reset = request.getParameter("reset");
        String setLocked = request.getParameter("setLocked");
        Boolean lockedBool = false;
        AdminFunctions a = new AdminFunctions();
        
        if(delete!=null)
        {
            int memberId = Integer.parseInt(delete);
            a.memberDelete(memberId);
            response.sendRedirect("admin.jsp");
        }
        
        if(reset!=null)
        {
            int memberId = Integer.parseInt(reset);
            a.memberResetPassword(memberId);
            response.sendRedirect("admin.jsp");
        }
        
        if(locked!=null && setLocked!=null)
        {
            int memberId = Integer.parseInt(setLocked);
            lockedBool = Boolean.parseBoolean(locked);
            if(!lockedBool)
            {
                lockedBool = true;
                a.memberSetLocked(memberId, lockedBool);
            }
            else
            {
                lockedBool = false;
                a.memberSetLocked(memberId, lockedBool);
            }
            response.sendRedirect("admin.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
