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
public class UpgradedMember {
    private int idMember;
    private String paypalAccountInformation;
    private int idStyle;

    /**
     *
     */
    public UpgradedMember() {
    }

    /**
     *
     * @param idMember
     * @param paypalAccountInformation
     * @param idStyle
     */
    public UpgradedMember(int idMember, String paypalAccountInformation, int idStyle) {
        this.idMember = idMember;
        this.paypalAccountInformation = paypalAccountInformation;
        this.idStyle = idStyle;
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
    public String getPaypalAccountInformation() {
        return paypalAccountInformation;
    }

    /**
     *
     * @param paypalAccountInformation
     */
    public void setPaypalAccountInformation(String paypalAccountInformation) {
        this.paypalAccountInformation = paypalAccountInformation;
    }

    /**
     *
     * @return
     */
    public int getIdStyle() {
        return idStyle;
    }

    /**
     *
     * @param idStyle
     */
    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    @Override
    public String toString() {
        return "UpgradedMember{" + "idMember=" + idMember + ", paypalAccountInformation=" + paypalAccountInformation + ", idStyle=" + idStyle + '}';
    }
    
    
}
