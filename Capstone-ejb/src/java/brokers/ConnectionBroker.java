/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author sebaslab
 */
public class ConnectionBroker {
    
    /**
     *
     */
    protected DataSource ds;
    private static ConnectionBroker cb;
    
    /**
     * Create the connection broker with a singleton approach in mind
     */
    private ConnectionBroker(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            InitialContext ic = new InitialContext();
            ds = (DataSource)ic.lookup("jdbc/GoldTree");
             
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * this is the method you call to get the singleton connection
     * @return
     */
    public static ConnectionBroker getConnection(){
        
        if(cb == null){
            cb = new ConnectionBroker();
            return cb;
        }
        else{
            return cb;
        }
    }
    
    /**
     *
     * @return
     */
    public DataSource getDataSource(){
        return ds;
    }
    
}
