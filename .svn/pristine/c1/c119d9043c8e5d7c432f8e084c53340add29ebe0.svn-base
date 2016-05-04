/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brokers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CPRG352
 */
public class ShoppingCartBroker {
    private Connection conn;
    private ConnectionBroker cb;
    
    /**
     * Creates a shopping cart broker (establishes a connection to the connection broker)
     */
    public ShoppingCartBroker()
    {
        try {
            cb = ConnectionBroker.getConnection();
            conn=cb.getDataSource().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Adds a photo to the user's shopping cart
     * @param userid the id of shopping cart's user
     * @param photoid the id of the photo being added to the shopping cart
     * @param duration the number of months the user is licensing the photo for 
     * @param exclusive a boolean representing whether or not the user wants an exclusive license or not
     */
    public void addPhotoToCart (int userid, int photoid, int duration, boolean exclusive)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call addPhotoToShoppingCart(?,?,?,?)");
            
            cs1.setInt(1,userid);
            cs1.setInt(2,photoid);
            cs1.setInt(3,duration);
            cs1.setBoolean(4, exclusive);
            
            cs1.execute();
            
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * removes a photo from the user's shopping car
     * @param userid the id of the shopping cart's user
     * @param photoid the id of the photo to be removed 
     */
    public void removePhotoFromShoppingCart (int userid, int photoid)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call shoppingCartRemovePhoto(?,?)");
            
            cs1.setInt(1,userid);
            cs1.setInt(2,photoid);
            
            cs1.execute();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * removes all of the photos from a users shopping cart
     * @param userid the id of the shopping cart's user
     */
    public void emptyShoppingCart (int userid)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call shoppingCartEmpty(?)");
            
            cs1.setInt(1,userid);
            
            cs1.execute();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * returns the html code for the table that contains the shopping cart items and includes a link to checkout and empty cart. also calculates and displays the sum of the shopping cart
     * @param userid the id of the shopping cart's user
     * @return the html code for shopping cart.
     */
    public String getShoppingCart (int userid)
    {
        String HTMLcode ="";
        
        try {
            CallableStatement cs1 = conn.prepareCall("call shoppingCartGetCart(?)");
            
            cs1.setInt(1,userid);
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            int sum = 0;
            HTMLcode = "<table><th>Photo Title</th><th>Price per month</th><th>Duration (months)</th><th>Remove</th>";
            while (rs.next())
            {
                /**
                 * getString(1): title of the photo being bought
                 * getInt(2): exclusive price of the photo
                 * getInt(3): regular price of the photo
                 * getInt(4): duration ( number of months for the license)
                 * getInt(5): exclusive boolean ( 1 = exclusive, 0 = regular )
                 * getInt(6): id of the photo being bought
                 */
                HTMLcode += "<tr>";
                HTMLcode += "<td>" + rs.getString(1) + "</td>";
                if(rs.getInt(5) == 0)
                {
                    HTMLcode += "<td>Regular Price: $" + rs.getInt(3) + "</td>";
                    sum += rs.getInt(3) * rs.getInt(4);
                }
                else
                {
                    HTMLcode += "<td>Exclusive Price: $" + rs.getInt(2) + "</td>";
                    sum += rs.getInt(2) * rs.getInt(4);
                }
                HTMLcode += "<td>" + rs.getInt(4) + "</td>";
                HTMLcode += "<td><a href='ShoppingCartOperations?removeFromCart="+ rs.getInt(6)+"' class='upgradeLink'>Remove</a></td>";
                HTMLcode +="</tr>";
            }
            // end of table, and after table, stuff: 
            HTMLcode +="</table><br/>sum = $" + sum + "<br/>"
                    + "<a href=\"ShoppingCartOperations?emptyCart=true\" class='upgradeLink'>Empty Cart</a> <br/>"
                    + "<a href=\"ShoppingCartOperations?checkout=true\" class='upgradeLink'>Checkout</a>";
            if (sum == 0) //shopping cart is empty
            {
                HTMLcode ="Your Shopping Cart is Empty. <a href=\"index.jsp\" class='upgradeLink'>Browse Photos.</a>";
            }
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return HTMLcode;
    }

    /**
     * Save the shopping cart items to the transaction history
     * @param userid
     */
    public void checkout(int userid)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call shoppingCartGetCart(?)");
            
            cs1.setInt(1,userid);
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            while (rs.next())
            {
                /**
                 * getString(1): title of the photo being bought
                 * getInt(2): exclusive price of the photo
                 * getInt(3): regular price of the photo
                 * getInt(4): duration ( number of months for the license)
                 * getInt(5): exclusive boolean ( 1 = exclusive, 0 = regular )
                 * getInt(6): id of the photo being bought
                 */
               try {
                    CallableStatement cs2 = conn.prepareCall("call shoppingCartCheckoutItem(?,?,?,?)");

                    cs2.setInt(1,userid);
                    cs2.setInt(2,rs.getInt(6));
                    cs2.setInt(3,rs.getInt(4));
                    cs2.setBoolean(4, rs.getBoolean(5));

                    cs2.execute();

                    cs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gets the transaction list of all the item in the transaction history
     * and will also create the HTML code required to print out
     * @param userid
     * @return
     */
    public String getTransactionList (int userid)
    {
        String HTMLcode ="";
        
        try {
            CallableStatement cs1 = conn.prepareCall("call transactionsGetList(?)");
            
            cs1.setInt(1,userid);
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            HTMLcode = "<table style='text-align:center;'><th>Photo Title</th><th>Photographer</th><th>Price per month</th><th>Duration (months)</th><th>Customer</th><th>View Photo</th><th>Date of Purchase(YYYY-MM-DD)</th>";
            while (rs.next())
            {
                /**
                 * getString(1): title of the photo being bought
                 * getInt(2): exclusive price of the photo
                 * getInt(3): regular price of the photo
                 * getInt(4): duration ( number of months for the license)
                 * getInt(5): exclusive boolean ( 1 = exclusive, 0 = regular )
                 * getInt(6): id of the photo being bought
                 * getString(7): photographers first name
                 * getString(8): photographers last name
                 * getInt(9): customer's ID
                 * getString(10): location of the file
                 * getDate(11): date of purchase
                 */
                boolean purchase = false; 
                if(rs.getInt(9) == userid) //true if the current transaction is one made by the current user, false if it was another user who bought the current user's photo
                {
                    purchase = true;
                }
                
                HTMLcode += "<tr>";
                HTMLcode += "<td>" + rs.getString(1) + "</td>";
                if(purchase)
                {
                     HTMLcode += "<td>" + rs.getString(7) + " " + rs.getString(8) + "</td>";
                }
                else
                {
                    HTMLcode += "<td><b>You</b></td>";
                }
                if(rs.getInt(5) == 0)
                {
                    HTMLcode += "<td>Regular Price: $" + rs.getInt(3) + "</td>";
                }
                else
                {
                    HTMLcode += "<td>Exclusive Price: $" + rs.getInt(2) + "</td>";
                }
                HTMLcode += "<td>" + rs.getInt(4) + "</td>";
                if(purchase)
                {
                     HTMLcode += "<td><b>You</b></td>";
                }
                else
                {
                    HTMLcode += "<td>another user</td>";
                }
                HTMLcode += "<td><a href=\""+ rs.getString(10)+"\" class='upgradeLink'>View Photo</a></td>";
                HTMLcode += "<td>" + rs.getDate(11) + "</td>";
                HTMLcode +="</tr>";
            }
            // end of table, and after table, stuff: 
            HTMLcode +="</table>";
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return HTMLcode;
    }
    /**
     * Closes the connection
     */
    public void close()
    {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
