/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagehelpers;
import core.AdminFunctions;
import domains.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author 633630
 */
public class AdminPageHelper {
    
    public AdminPageHelper()
    {
        
    }
    
    /**
     * display the HTML code to display the table that contains all of the users
     * and the extra functions to manage these users
     * @return String HTML code for the user table
     */
    public String createUserTable() throws ClassNotFoundException, SQLException, NamingException
    {
        String table = "<table class='userTable' border='1'><th>UserID</th><th>Email</th><th>Type</th><th>Locked</th><th>Reset PW</th><th>Delete</th>";
        AdminFunctions af = new AdminFunctions();
        
        ArrayList<Member> list = af.memberGetAll();

        for(int i = 0; i < list.size(); i++)
        {
            table += "<tr><td class='id'>" + list.get(i).getIdMember() + "</td><td>" + list.get(i).getEmail() + "</td><td>" + list.get(i).getAccountType() + "</td><td><a href='AdminOperations?setLocked=" + list.get(i).getIdMember() + "&locked="
                    + list.get(i).getIsLock() + "'>" + list.get(i).getIsLock() + "</a></td><td><a href='AdminOperations?reset=" + list.get(i).getIdMember() + "'>Reset</a></td><td><a href='AdminOperations?delete="
                    + list.get(i).getIdMember() + "'>Delete</a></td></tr>";
        }
        table += "</table>";
        
        return table;
    }
    
}
