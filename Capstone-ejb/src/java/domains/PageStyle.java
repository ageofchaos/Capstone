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
public class PageStyle {
    private int idStyle;
    private String fontType;
    private String fontColor;
    private String backgroundStyle;

    /**
     *
     */
    public PageStyle() {
    }

    /**
     *
     * @param idStyle
     * @param fontType
     * @param fontColor
     * @param backgroundStyle
     */
    public PageStyle(int idStyle, String fontType, String fontColor, String backgroundStyle) {
        this.idStyle = idStyle;
        this.fontType = fontType;
        this.fontColor = fontColor;
        this.backgroundStyle = backgroundStyle;
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

    /**
     *
     * @return
     */
    public String getFontType() {
        return fontType;
    }

    /**
     *
     * @param fontType
     */
    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    /**
     *
     * @return
     */
    public String getFontColor() {
        return fontColor;
    }

    /**
     *
     * @param fontColor
     */
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    /**
     *
     * @return
     */
    public String getBackgroundStyle() {
        return backgroundStyle;
    }

    /**
     *
     * @param backgroundStyle
     */
    public void setBackgroundStyle(String backgroundStyle) {
        this.backgroundStyle = backgroundStyle;
    }

    @Override
    public String toString() {
        return "PageStyle{" + "idStyle=" + idStyle + ", fontType=" + fontType + ", fontColor=" + fontColor + ", backgroundStyle=" + backgroundStyle + '}';
    }
    
    
    
}
