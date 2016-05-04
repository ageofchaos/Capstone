/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import brokers.memberBroker;
import domains.Member;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author sebaslab
 */
@Stateless
@LocalBean
public class AdminFunctions {

    /**
     *
     */
    public AdminFunctions(){
        
    }
    
    /**
     * get all the member in the database
     * @return
     */
    public ArrayList memberGetAll(){
        memberBroker broker = new memberBroker();
        ArrayList<Member> list = broker.memberGetAllBroker();
        return list;
    }
    
    /**
     * delete a member in the database
     * @param idMember
     */
    public void memberDelete(int idMember){
        memberBroker broker = new memberBroker();
        broker.memberDeleteBroker(idMember);
    }
    
    /**
     * change the lock status of a member on the database
     * @param idMember
     * @param lockStatus
     */
    public void memberSetLocked(int idMember, boolean lockStatus)
    {
        memberBroker broker = new memberBroker();
        broker.memberSetLockedBroker(idMember, lockStatus);
    }
    
    /**
     * reset the password of a member in the database
     * @param idMember
     */
    public void memberResetPassword(int idMember)
    {
        memberBroker broker = new memberBroker();
        broker.memberResetPasswordBroker(idMember);
    }
}
