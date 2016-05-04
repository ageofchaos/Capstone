/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pagehelpers;
import brokers.PhotoBroker;
import core.AdminFunctions;
import core.PhotoFunctions;
import domains.Member;
import domains.Photo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author CPRG352
 */
public class PhotoDisplayHelper {

    PhotoBroker pb;
    PhotoFunctions pf;
    public PhotoDisplayHelper() {
    }
    
    /**
     * displays the top photo in a given tag
     * @param tagName the tag for which the photo should be displayed
     * @return String HTML code for the photo
     */
    public String displayTag(String tagName)
    {
        pb = new PhotoBroker();
        String htmlCodeForTag ="";
        String photoLocation = pb.photoGetTopPhotoInTag(tagName);
        
        htmlCodeForTag =    "<div onclick=\"location.href='SearchOperation?searchValue=" + tagName+ "';\" class=\"tagThumbnail\" style=\"background-image: url('"+ photoLocation +"');\">\n" +
                            "<div class=\"tagThumbnailTitle\">\n" +
                            "<img src=\"src/icons/tagIcon.png\" class=\"tagIcon\"><h3><a href=\"SearchOperation?searchValue="+ tagName +"\">"+ tagName +"</a></h3> \n" +
                            "</div>\n" +
                            "</div>";
        pb.close();
        return htmlCodeForTag;
    }
    
    /**
     * displays the full size picture with all the other information that goes with it
     * @param photoID the photo id for which the photo should be displayed
     * @return String HTML code for the photo full sized
     */
    public String displayFullPhoto(int photoID) throws SQLException
    {
        pb = new PhotoBroker();
        
        Photo photo = pb.photoGetPhotoValues(photoID);
        String htmlCodeForFullPhoto ="";
        
        htmlCodeForFullPhoto =  "<div class='img-wrap'>\n" +
                                "            <div class=\"description\" >\n" +
                                "                <div class=\"descHeader\">\n" +
                                "                    <h1 >"+ photo.getTitle() + "</h1>\n" +
                                "                    <h4>" + getArtistForPhoto(photoID)+ "</h4>\n" +
                                "                </div>\n" +
                                "                <div class='licensing'>\n" +
                                "                    <div style=\"padding:5px; display:inline\">Add License to Cart</div>\n" +
                                "                    <form action=\"ShoppingCartOperations\" method=\"POST\"><input type=\"number\" name=\"months\" placeholder=\"Number of Months\" required> <input type=\"submit\" name=\"exclusive\" value=\"Exclusive License\">or<input type=\"submit\" name=\"regular\" value=\"Regular License\"><input type=\"hidden\" name=\"photoid\" value=\""+ photoID+ "\"></form>\n" +
                                "                </div>\n" +
                                "                <div class=\"descText\">\n" +
                                "                    " + photo.getDescription() + getTagsForPhoto(photoID)+" View Count:"+photo.getViewCount()+"\n" +
                                "                </div>\n" +
                                "            </div>\n" +
                                "            <img id=\"img\" src=\"" + photo.getLocationPhoto() +"\" data-adaptive-background='1'>\n" +
                                "            <div class=\"cropContainer\">\n" +
                                "                <div class=\"center-cropped\"style=\"background-image: url('" + photo.getLocationPhoto() +"');\"></div>\n" +
                                "            </div>\n" +
                                "        </div>";
        pb.close();
        return htmlCodeForFullPhoto;
    }
    
    /**
     * display the full size photo with modification for the upgraded member
     * @param photoID the photo id for which the photo should be displayed
     * @return String HTML code for the photo full sized
     */
    public String displayFullPhotoUpgraded(int photoID) throws SQLException
    {
        pb = new PhotoBroker();
        ArrayList<String> tagList = pb.photoGetTags(photoID);
        String tags = "";
        for(int i = 0; i < tagList.size(); i++)
        {
            tags += tagList.get(i);
            if(i!= tagList.size() - 1)
                tags+= " ";
        }
        pf = new PhotoFunctions();
        int regularPrice = pf.getRegularPrice(photoID);
        int exclusivePrice = pf.getExclusivePrice(photoID);
        Photo photo = pb.photoGetPhotoValues(photoID);
        String htmlCodeForFullPhoto ="";
        htmlCodeForFullPhoto =  "<div class='img-wrap'>\n" +
                                "            <div class=\"description\" >\n" +
                                "                <div class=\"descHeader\">\n" +
                                "                    <form action='EditPhoto?photoId=" + photo.getIdPhoto() + "&oldTags=" + tags + "' method='POST'>Title: <input type='text' value='"+ photo.getTitle() + "' name='title'>\n" +
                                "                    <h4>" + getArtistForPhoto(photoID)+ "</h4>\n" + 
                                "                </div>\n" +
                                "                <div class=\"descText\">\n" +
                                "                    Description: <input type='textbox' value='" + photo.getDescription() + "' name='description'>" + "<br/>Tags: <input type='text' value='" + tags + "' name='tags'>"+
                                "                   <br/>Regular Price: <input type='number' name='regularPrice' value='" + regularPrice + "'><br/>Exclusive Price: <input type='number' name='exclusivePrice' value='" + exclusivePrice + "'><br/>View Count: "+photo.getViewCount()+"<br/>" +
                                "<input type='submit' value='Update'></form><br/><a href='DeletePhoto?photoId=" + photo.getIdPhoto() + "'>Delete</a>" + "\n" +
                                "                </div>\n" +
                                "            </div>\n" +
                                "            <img id=\"img\" src=\"" + photo.getLocationPhoto() +"\" data-adaptive-background='1'>\n" +
                                "            <div class=\"cropContainer\">\n" +
                                "                <div class=\"center-cropped\"style=\"background-image: url('" + photo.getLocationPhoto() +"');\"></div>\n" +
                                "            </div>\n" +
                                "        </div>";
        pb.close();
        return htmlCodeForFullPhoto;
    }
    
    /**
     * simply gets the tags for a given picture
     * @param photoID the photo id for which the tags should be displayed
     * @return String HTML code for the tags
     */
    private String getTagsForPhoto(int photoID)
    {
        pb = new PhotoBroker();
        String htmlCode ="<br/>";
        ArrayList<String> tags = pb.photoGetTags(photoID);
        
        for(int i = 0; i < tags.size() ; i++)
        {
            htmlCode = htmlCode + "<a href=\"SearchOperation?searchValue="+ tags.get(i) +"\">"+ tags.get(i) +"</a> ";
        }
        
        pb.close();
        return htmlCode;
    }
    
    /**
     * returns the artist for a given photo
     * @param photoID the photo id for which the artist should be displayed
     * @return String HTML code for the artist
     */
    private String getArtistForPhoto(int photoID)
    {
        pb = new PhotoBroker();
        Member artist = pb.photoGetMemberNameFromPhotoID(photoID);
        String htmlCode = "<a href=\"fileupload.jsp?artist="+ artist.getEmail() +"\">"+
                    artist.getFirstName()+" "+artist.getLastName() +"</a> ";
        pb.close();
        return htmlCode;
    }
    /**
     * display the Thumbnail for photo with modification for the member
     * @param photo the photo object for which the photo thumbnail should be displayed
     * @return String HTML code for the photo thumbnail
     */
    public String displayPhotoThumbnail(Photo photo)
    {
        String htmlCode ="";
        htmlCode =    "<div onclick=\"location.href='photo.jsp?photo=" + photo.getIdPhoto()+ "';\" class=\"tagThumbnail\" style=\"background-image: url('"+ photo.getLocationPhoto() +"');\">\n" +
                        "<div class=\"tagThumbnailTitle\">\n" +
                        "<h3><a href=\"photo.jsp?photo="+ photo.getIdPhoto() +"\">"+ photo.getTitle() +"</a></h3> \n" +
                        "</div>\n" +
                        "</div>";
        return htmlCode;      
    }
     /**
     * display the Thumbnail for photo with modification for the upgraded member
     * @param photo the photo object for which the photo thumbnail should be displayed
     * @return String HTML code for the photo thumbnail
     */
    public String displayPhotoThumbnailUpgraded(Photo photo, String artist)
    {
        String htmlCode ="";
        htmlCode =    "<div onclick=\"location.href='photoUpgraded.jsp?artist=" + artist + "&photo=" + photo.getIdPhoto()+ "';\" class=\"tagThumbnail\" style=\"background-image: url('"+ photo.getLocationPhoto() +"');\">\n" +
                        "<div class=\"tagThumbnailTitle\">\n" +
                        "<h3><a href=\"photoUpgraded.jsp?artist=" + artist + "&photo="+ photo.getIdPhoto() +"\">"+ photo.getTitle() +"</a></h3> \n" +
                        "</div>\n" +
                        "</div>";
        return htmlCode;      
    }
    
}
