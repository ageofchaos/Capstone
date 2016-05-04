/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package operations;

import brokers.ShoppingCartBroker;
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
 * @author CPRG352
 */
@WebServlet(name = "ShoppingCartOperations", urlPatterns = {"/ShoppingCartOperations"})
public class ShoppingCartOperations extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * This servlet is used to handle the shopping cart, checking out, emptying, removing, or adding items to the cart.
     * It takes parameters from shoppingcart.jsp in order to do this.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        try
        {
            int userid = (int) session.getAttribute("userId"); // if this causes a null pointer exception, it means the user is not logged in
            
            /**
             * adds an item into the user's shopping cart
             */
            if(request.getParameter("exclusive") != null || request.getParameter("regular") != null )
            {
                String months = request.getParameter("months");
                try
                {
                    int duration = Integer.parseInt(months); // this will cause a number format exception if the value stored in "months" is not an int
                    if( duration < 1)
                    {
                        response.sendRedirect("index.jsp?message=Number of months can not be negative");
                    }
                    else
                    {
                        boolean exclusive = false;
                        if (request.getParameter("exclusive") != null)
                        {
                            exclusive = true;
                        }
                        ShoppingCartBroker scb = new ShoppingCartBroker();
                        scb.addPhotoToCart(userid, Integer.parseInt(request.getParameter("photoid")), duration, exclusive);
                        scb.close();
                        response.sendRedirect("shoppingCart.jsp");
                    }
                }
                catch(NumberFormatException e) 
                {
                    response.sendRedirect("index.jsp?message=Not a number");
                }
                
            }
            /**
             * removes one item from the user's shopping cart
             */
            else if(request.getParameter("removeFromCart") != null)
            {
                try
                {
                    int photoid = Integer.parseInt(request.getParameter("removeFromCart"));
                    ShoppingCartBroker scb = new ShoppingCartBroker();
                    scb.removePhotoFromShoppingCart(userid, photoid);
                    scb.close();
                    response.sendRedirect("shoppingCart.jsp");
                }
                catch(NumberFormatException e) 
                {
                    response.sendRedirect("index.jsp?message=Not a number");
                }
            }
            /**
             * Removes all items from the user's shopping cart
             */
            else if(request.getParameter("emptyCart") != null)
            {
                ShoppingCartBroker scb = new ShoppingCartBroker();
                scb.emptyShoppingCart(userid);
                scb.close();
                response.sendRedirect("shoppingCart.jsp");
            }
            /**
             * cycles through all the items in the user's shopping cart and checks them out into
             * transaction items. After that, it empties out the shopping cart.
             */
            else if(request.getParameter("checkout") != null)
            {
                ShoppingCartBroker scb = new ShoppingCartBroker();
                scb.checkout(userid);
                scb.emptyShoppingCart(userid); 
                scb.close();
                response.sendRedirect("transaction.jsp");
            }
            else
            {
                response.sendRedirect("index.jsp");
            }
        }
        catch (NullPointerException e) 
        {
            response.sendRedirect("index.jsp?message=Log in or register to purchase photos");
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
