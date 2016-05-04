/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokers;

import domains.Member;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author sebaslab
 */
public class memberBroker {
    
    private Connection conn;
    private ConnectionBroker cb;
    
    /**
     *
     */
    public memberBroker(){
        try {
            cb = ConnectionBroker.getConnection();
            conn=cb.getDataSource().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
}
    }
    
    /**
     * validate by checking if the given username and password are 
     * part of the database
     * @param username
     * @param password
     * @return
     */
    public boolean memberValidateBroker(String username, String password){
        boolean check = false;
        try {
                       
            CallableStatement cs1 = conn.prepareCall("call validateMember(?,?)");
            
            cs1.setString(1,username);
            cs1.setString(2,password);
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            rs.next();
            if(rs.getInt(1) > 0)
                check = true;
            
            cs1.close();
            rs.close();
            conn.close();
        }catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    /**
     * checks if the member is locked or not
     * @param username
     * @return
     */
    public boolean memberCheckLockedBroker(String username){
        boolean check = false;
        
        try {
            
            CallableStatement cs1 = conn.prepareCall("call memberGetLocked(?)");
            
            cs1.setString(1,username);
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            rs.next();
            if(rs.getBoolean(1))
                check = true;
            
            cs1.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    /**
     * retrieve the member type with the given username
     * @param username
     * @return
     */
    public char memberCheckTypeBroker(String username){
        char check = 0;
        
        try {
                       
            CallableStatement cs1 = conn.prepareCall("call memberGetType(?)");
            
            cs1.setString(1,username);
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            rs.next();
            check = rs.getString(1).charAt(0);
            
            cs1.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    /**
     * get all of the info/attribute of a specific member
     * @param username
     * @return
     */
    public Member memberGetInfoBroker(String username){
        Member member = null;
        
        try {
                       
            CallableStatement cs1 = conn.prepareCall("call memberGetMemberInfo(?)");
            
            cs1.setString(1,username);
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            rs.next();
            member = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getBoolean(6), rs.getString(7).charAt(0),
                        rs.getString(8),rs.getString(9));
            
            cs1.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member;
    }
    
    /**
     * check if the given username exist
     * @param username
     * @return
     */
    public boolean memberCheckExistsBroker(String username)
    {
        boolean check = false;

        try {
            CallableStatement cs1 = conn.prepareCall("call memberExists(?)");

            cs1.setString(1,username);

            ResultSet rs=null;

            rs = cs1.executeQuery();
            rs.next();
            if(rs.getInt(1) > 0)
                check = true;

            cs1.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    /**
     * add a new user to the database and all of its attribute
     * @param firstname
     * @param lastname
     * @param username
     * @param password
     * @param locked
     * @param type
     */
    public void memberAddBroker(String firstname, String lastname, String username, 
            String password, Boolean locked, String type){
        
        try {
            
            CallableStatement cs1 = conn.prepareCall("call addMember(?,?,?,?,?,?)");
            
            cs1.setString(1,firstname);
            cs1.setString(2,lastname);
            cs1.setString(3,username);
            cs1.setString(4,password);
            cs1.setBoolean(5,locked);
            cs1.setString(6,type);
            
            cs1.execute();
            
            cs1.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * deletes a member from the database with the given member id
     * @param idMember
     */
    public void memberDeleteBroker(int idMember){
        
        try {
            
            CallableStatement cs1 = conn.prepareCall("call deleteMember(?)");
            
            cs1.setInt(1,idMember);
            
            cs1.execute();
            
            cs1.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get all of the member that are in the database
     * @return
     */
    public ArrayList<Member> memberGetAllBroker(){
        
        try {
            
            CallableStatement cs1 = conn.prepareCall("call memberGetAllData()");
            
            cs1.execute();
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            
            ArrayList<Member> list = new ArrayList();
            
            while (rs.next()){
                Member member = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getBoolean(6), rs.getString(7).charAt(0),rs.getString(8),
                        rs.getString(9));
                list.add(member);
            }
            cs1.close();
            rs.close();
            conn.close();
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * reset the password for a member using the member id
     * @param idMember
     */
    public void memberResetPasswordBroker(int idMember)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call memberResetPassword(?)");
            
            cs1.setInt(1,idMember);
            
            cs1.execute();
            
            cs1.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * change the lock status of a member in the database
     * @param idMember
     * @param lockStatus
     */
    public void memberSetLockedBroker(int idMember, boolean lockStatus)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call memberSetLocked(?, ?)");
            
            cs1.setInt(1,idMember);
            cs1.setBoolean(2, lockStatus);
            cs1.execute();
            
            cs1.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get the id of a member using the username
     * @param username
     * @return
     */
    public int memberGetIdBroker(String username)
    {
        int memberId = 0;

            try {
                CallableStatement cs1 = conn.prepareCall("call memberGetId(?)");

                cs1.setString(1,username);

                ResultSet rs=null;

                rs = cs1.executeQuery();
                rs.next();
                
                memberId = rs.getInt(1);
                

                cs1.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        return memberId;
    }
    
    /**
     * save the new style for a member to the database using the member id
     * @param id
     * @param style
     */
    public void memberSaveStyleBroker(int id,String style)
    {
        try {

                CallableStatement cs1 = conn.prepareCall("call memberSaveStyle(?,?)");

                cs1.setInt(1,id);
                cs1.setString(2,style);

                cs1.execute();
                
                cs1.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * get the info of a upgraded member
     * @param username
     * @param paypalInfo
     */
    public void memberUpgradeInfoBroker(String username, String paypalInfo)
    {
        try {

                CallableStatement cs1 = conn.prepareCall("call memberUpgradeInfo(?,?)");

                cs1.setString(1,username);
                cs1.setString(2,paypalInfo);

                cs1.execute();
                
                cs1.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * get the style value of a upgraded member
     * @param id
     * @return
     */
    public String memberGetStyleBroker(int id)
    {
        String style = null;
        try {

                CallableStatement cs1 = conn.prepareCall("call memberGetStyle(?)");

                cs1.setInt(1,id);
                ResultSet rs=null;

                rs = cs1.executeQuery();
                rs.next();
                
                style = rs.getString(1);

                cs1.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        return style;
    }
    
    /**
     * change the password of a member in the database using his username and 
     * replacing the old password with the new password
     * @param username
     * @param password
     */
    public void memberChangePasswordBroker(String username, String password)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call memberChangePassword(?, ?)");
            
            cs1.setString(1,username);
            cs1.setString(2, password);
            cs1.execute();
            
            cs1.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
