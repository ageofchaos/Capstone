/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagehelpers;

import core.MemberFunctions;
import domains.Member;
import java.util.ArrayList;

/**
 *
 * @author 633630
 */
public class AccountPageHelper {
    
    public AccountPageHelper()
    {
        
    }
    
    /**
     * display the a detailed form for a normal member
     * @param username for who the form should appear for
     * @return String HTML code for the detail form member
     */
    public String createEditDetailsFormMember(String username)
    { 
        MemberFunctions mf = new MemberFunctions();
        Member m = mf.getMemberInfo(username);
        String form = "<h1>Welcome, " + m.getFirstName() + " " + m.getLastName() + "</h1><form action='MemberOperations' type='POST'>"
                + "New password: <input type='password' name='passwordChange'><br/>"
                + "<br/><input type='submit' name='member' id='submitbutton' value='Submit'>"
                + "<br/>Upgrade User: <a href='upgradedregister.jsp' class='upgradeLink'>Click here to upgrade account</a>"
                + "</form>";
        return form;
    }
    
    /**
     * display the a detailed form for a upgraded member
     * @param username for who the form should appear for
     * @return String HTML code for the detail form member
     */
    public String createEditDetailsFormUpgraded(String username)
    { 
        MemberFunctions mf = new MemberFunctions();
        Member m = mf.getMemberInfo(username);
        String form = "<h1>Welcome, " + m.getFirstName() + " " + m.getLastName() + "</h1><form action='MemberOperations' type='POST'>"
                + "New password: <input type='password' name='passwordChange'><br/>"
                + "<br/><input type='submit' name='upgraded' id='submitbutton' value='Submit'>"
                + "</form>";
        return form;
    }
    
}
