/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import brokers.memberBroker;
import domains.Member;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author sebaslab
 */
@Stateless
@LocalBean
public class MemberFunctions {

    /**
     *
     */
    public MemberFunctions(){
        
    }
    
    /**
     * check if the given username and password are the same them
     * the one found in the database
     * @param username
     * @param password
     * @return
     */
    public boolean memberValidate(String username, String password){
        memberBroker broker = new memberBroker();
        boolean check = broker.memberValidateBroker(username, password);
        return check;
    }
    
    /**
     * checks if the given member is locked or not
     * @param username
     * @return
     */
    public boolean memberCheckLocked(String username){
        memberBroker broker = new memberBroker();
        boolean check = broker.memberCheckLockedBroker(username);
        return check;
    }

    /**
     * Checks what type the member is and returns it
     * @param username
     * @return
     */
    public char memberCheckType(String username){
        memberBroker broker = new memberBroker();
        char check = broker.memberCheckTypeBroker(username);
        return check;
    }
    
    /**
     * checks if the given username is part of the database
     * @param username
     * @return
     */
    public boolean memberCheckExists(String username){
        memberBroker broker = new memberBroker();
        boolean check = broker.memberCheckExistsBroker(username);
        return check;
    }
    
    /**
     * adds a new members to the database and all of its values
     * @param firstname
     * @param lastname
     * @param username
     * @param password
     * @param locked
     * @param type
     */
    public void memberAdd(String firstname, String lastname, String username, String password, boolean locked, String type){
        memberBroker broker = new memberBroker();
        broker.memberAddBroker(firstname, lastname, username, password, locked, type);
    }
    
    /**
     * get the id of a member with a given username
     * @param username
     * @return
     */
    public int memberGetId(String username)
    {
        memberBroker broker = new memberBroker();
        return broker.memberGetIdBroker(username);
    }

    /**
     * Save the style for a given member
     * @param id
     * @param hiddenbgc
     * @param hiddenft
     * @param hiddenfc
     * @param hiddenbgp
     * @return
     */
    public String memberSaveStyle(int id,String hiddenbgc,
            String hiddenft,String hiddenfc,String hiddenbgp)
    {
        memberBroker broker = new memberBroker();
        String style= (hiddenbgc+","+hiddenft+","+hiddenfc+","+hiddenbgp);
        broker.memberSaveStyleBroker(id, style);
        return "Success";
    }
    
    /**
     * get the style for a given member
     * @param userId
     * @return
     */
    public String searchStyleMember(int userId){
        memberBroker broker = new memberBroker();
        String style = broker.memberGetStyleBroker(userId);
        
        
        return style;
    } 
    
    /**
     * get the info for a specific member
     * @param username
     * @return
     */
    public Member getMemberInfo(String username)
    {
        memberBroker broker = new memberBroker();
        Member member = broker.memberGetInfoBroker(username);
        
        return member;
    } 

    /**
     * get the info for a specific upgraded member
     * @param username
     * @param paypalInfo
     */
    public void memberUpgradeInfo(String username, String paypalInfo)
    {
        memberBroker broker = new memberBroker();
        broker.memberUpgradeInfoBroker(username, paypalInfo);
        
    }
    
    /**
     * change the password for a given username
     * @param username
     * @param password
     */
    public void memberChangePassword(String username, String password)
    {
        memberBroker broker = new memberBroker();
        broker.memberChangePasswordBroker(username, password);
    }
}
