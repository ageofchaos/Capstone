/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import core.MemberFunctions;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sebaslab
 */
@WebServlet(name = "StyleOperations", urlPatterns = {"/StyleOperations"})
public class StyleOperations extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * This servlet handles the saving of the gallery style.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hiddenbgc = request.getParameter("hiddenbgc");
        String hiddenft = request.getParameter("hiddenft");
        String hiddenfc = request.getParameter("hiddenfc");
        String hiddenbgp = request.getParameter("hiddenbgp");
        HttpSession session = request.getSession();
        
        if(hiddenbgc != null && !hiddenbgc.equals("") &&
                hiddenft !=null && !hiddenft.equals("") &&
                hiddenfc !=null && !hiddenfc.equals("") &&
                hiddenbgp !=null && !hiddenbgp.equals(""))
        {
            if(hiddenbgc.length()==1 && hiddenft.length()==1
                    && hiddenfc.length()==1 && hiddenbgp.length()==1)
            {
                if(Character.isDigit(hiddenbgc.charAt(0)) &&
                        Character.isDigit(hiddenft.charAt(0))&&
                        Character.isDigit(hiddenfc.charAt(0))&&
                        Character.isDigit(hiddenbgp.charAt(0)))
                {
                    MemberFunctions mf = new MemberFunctions();
                    mf.memberSaveStyle((Integer)session.getAttribute("userId"),
                           hiddenbgc,hiddenft,hiddenfc,hiddenbgp);
                }
            }
            response.sendRedirect("fileupload.jsp?artist="+session.getAttribute("username")+
                    "&message=Saved successfully");
        }
        else
            response.sendRedirect("fileupload.jsp?artist="+session.getAttribute("username")+
                    "&message=Save Unsuccessful");
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
