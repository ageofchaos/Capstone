/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

/**
 *
 * @author sebaslab
 */
public class Member {
    private int idMember;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isLock;
    private char accountType;
    private String paypalAccountInfo;
    private String pageStyle;

    /**
     *
     */
    public Member() {
    }

    /**
     *
     * @param idMember
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param isLock
     * @param accountType
     * @param paypalAccountInfo
     * @param pageStyle
     */
    public Member(int idMember, String firstName, String lastName, String email, String password,
            boolean isLock, char accountType, String paypalAccountInfo, String pageStyle) {
        this.idMember = idMember;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isLock = isLock;
        this.accountType = accountType;
        this.paypalAccountInfo = paypalAccountInfo;
        this.pageStyle = pageStyle;
    }

    /**
     *
     * @return
     */
    public int getIdMember() {
        return idMember;
    }

    /**
     *
     * @param idMember
     */
    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean getIsLock() {
        return isLock;
    }

    /**
     *
     * @param isLock
     */
    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    /**
     *
     * @return
     */
    public char getAccountType() {
        return accountType;
    }

    /**
     *
     * @param accountType
     */
    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }

    /**
     *
     * @return
     */
    public String getPaypalAccountInfo() {
        return paypalAccountInfo;
    }

    /**
     *
     * @param paypalAccountInfo
     */
    public void setPaypalAccountInfo(String paypalAccountInfo) {
        this.paypalAccountInfo = paypalAccountInfo;
    }

    /**
     *
     * @return
     */
    public String getPageStyle() {
        return pageStyle;
    }

    /**
     *
     * @param pageStyle
     */
    public void setPageStyle(String pageStyle) {
        this.pageStyle = pageStyle;
    }

    @Override
    public String toString() {
        return "Member{" + "idMember=" + idMember + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", isLock=" + isLock + ", accountType=" + accountType + ", paypalAccountInfo=" + paypalAccountInfo + ", pageStyle=" + pageStyle + '}';
    }

    
    
    
    
}
