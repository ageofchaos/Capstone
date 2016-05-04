/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import core.PhotoFunctions;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

/**
 *
 * @author 633630
 */
@WebServlet(name = "EditPhoto", urlPatterns = {"/EditPhoto"})
public class EditPhoto extends HttpServlet {
@EJB
    private PhotoFunctions pf;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * This servlet is used to change the details of a photo, which include the title, description, tags, regular price,
     * and exclusive price. It receives the necessary parameters from the photoUpgraded.jsp page, updating everything
     * at once. It sanitizes the parameters passed through to make sure there's no scripts, as well as checks the photo
     * id passed through to make sure the user owns that photo.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String tags = request.getParameter("tags");
        String description = request.getParameter("description");
        String oldTags = request.getParameter("oldTags");
        String regularPrice = request.getParameter("regularPrice");
        String exclusivePrice = request.getParameter("exclusivePrice");
        title = sanitize(title);
        tags = sanitize(tags);
        description = sanitize(description);
        String photoEdit = request.getParameter("photoId");
        int photoId = 0;
        if(photoEdit!=null)
            photoId = Integer.parseInt(photoEdit);
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        String artist = pf.getArtist(photoId);
        if(username.equals(artist) + photoEdit!=null && title!=null && !title.equals("") && description!=null && !description.equals("") && tags!=null && !tags.equals("") && exclusivePrice!=null && !exclusivePrice.equals("")&& regularPrice!=null && !regularPrice.equals(""))
        {
            int regularPriceInt = Integer.parseInt(regularPrice);
            int exclusivePriceInt = Integer.parseInt(exclusivePrice);
            String[] tagsList = tags.toLowerCase().split(" ");
            String[] oldTagsList = oldTags.toLowerCase().split(" ");
            pf.updatePhoto(photoId, description, title, regularPriceInt, exclusivePriceInt);
            pf.updateTags(photoId, tagsList, oldTagsList);
            response.sendRedirect("photoUpgraded.jsp?artist=" + (String)session.getAttribute("username") + "&photo=" + photoEdit);
        }
        else
            response.sendRedirect("fileupload.jsp?artist=" + (String)session.getAttribute("username") + "&message=Cannot edit photo");
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
            Logger.getLogger(MemberOperations.class.getName()).log(Level.SEVERE, null, ex);
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
