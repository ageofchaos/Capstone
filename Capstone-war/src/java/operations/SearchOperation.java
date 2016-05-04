/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import core.PhotoFunctions;
import fileoperations.UploadServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

/**
 *
 * @author sebaslab
 */
@WebServlet(name = "SearchOperation", urlPatterns = {"/SearchOperation"})
public class SearchOperation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * This servlet is used to search the database for the parameter being passed through from search.jsp.
     * It's input is sanitized to make sure there's no malicious code.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String search = request.getParameter("searchValue");        
        search = sanitize(search);
        if(search != null && !search.equals(""))
        {
            ArrayList photoList = new ArrayList();
            PhotoFunctions pf = new PhotoFunctions();
            String[] searchSplit = search.split(" ");
            photoList = pf.searchPhoto(searchSplit);
            request.getSession().setAttribute("photoList", photoList);
            response.sendRedirect("search.jsp?");
        }
        else{
            response.sendRedirect("index.jsp?message=No search Value entered");
        }
    } 
    
    private String sanitize(String dirtyInput)
    {
        String cleanInput = "";
        try {
            final String POLICY_LOCATION = getServletContext().getRealPath("WEB-INF/policy.xml");
            Policy policy = Policy.getInstance(POLICY_LOCATION);
            AntiSamy as = new AntiSamy();
            CleanResults cr = as.scan(dirtyInput, policy, AntiSamy.DOM);
            cleanInput = cr.getCleanHTML();
        } catch (PolicyException | ScanException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cleanInput;
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
