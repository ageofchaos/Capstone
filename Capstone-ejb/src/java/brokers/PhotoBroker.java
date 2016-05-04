/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokers;

import domains.Member;
import domains.Photo;
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
public class PhotoBroker {
    
    private Connection conn;
    private ConnectionBroker cb;
    
    /**
     * gets the connection by calling the singleton ConnectionBroker
     */
    public PhotoBroker(){
        try {
            cb = ConnectionBroker.getConnection();
            conn=cb.getDataSource().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * add a new photo to the database and all of it values
     * @param idMember
     * @param idLicensing
     * @param title
     * @param description
     * @param location
     */
    public void photoAddBroker(int idMember, int idLicensing, String title, 
            String description, String location){
        
        try {
            CallableStatement cs1 = conn.prepareCall("call addPhoto(?,?,?,?,?)");
            
            cs1.setInt(1,idMember);
            cs1.setInt(2,idLicensing);
            cs1.setString(3,title);
            cs1.setString(4,description);
            cs1.setString(5,location);
            
            cs1.execute();
            
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * add a new photo to the database and all of it values
     * @param idMember
     * @param idLicensing
     * @param title
     * @param description
     * @param location
     * @param price
     * @param exclusivePrice
     */
    public void photoAddBroker(int idMember, int idLicensing, String title, 
            String description, String location, int price, int exclusivePrice){
        
        try {
            CallableStatement cs1 = conn.prepareCall("call addPhotoWithPrices(?,?,?,?,?,?,?)");
            
            cs1.setInt(1,idMember);
            cs1.setInt(2,idLicensing);
            cs1.setString(3,title);
            cs1.setString(4,description);
            cs1.setString(5,location);
            cs1.setInt(6,price);
            cs1.setInt(7,exclusivePrice);
            
            cs1.execute();
            
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes a photo from the database using the photo id
     * @param idPhoto
     */
    public void photoDeleteBroker(int idPhoto){
        
        try {
            CallableStatement cs1 = conn.prepareCall("call deletePhoto(?)");
            
            cs1.setInt(1,idPhoto);
            
            cs1.execute();
            
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  add a tag to a photo by using a array of tags and the photo id
     * @param idPhoto
     * @param tags
     */
    public void photoAddTagsBroker(int idPhoto, String[] tags)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call addTag(?,?)");
            
            for(int i = 0; i < tags.length; i++)
            {
                cs1.setInt(1, idPhoto);
                cs1.setString(2, tags[i]);
                cs1.execute();
            }
            
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get the id of a photo by using its location
     * @param location
     * @return
     */
    public int photoGetIdBroker(String location)
    {
        int photoId = 0;
        
        try {
            CallableStatement cs1 = conn.prepareCall("call photoGetId(?)");
            
            cs1.setString(1,location);
            
            ResultSet rs = cs1.executeQuery();
            rs.next();
            photoId = rs.getInt(1);
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photoId;
    }
    
    /**
     * get all of the photos that are currently in the database
     * @return
     */
    public ArrayList<Photo> photoGetAllBroker(){
        
        try {
            CallableStatement cs1 = conn.prepareCall("call photoGetAllData()");
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            
            ArrayList<Photo> list = new ArrayList();
            
            while (rs.next()){
                Photo photo = new Photo(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),
                        rs.getInt(6), null, rs.getString(8));
                list.add(photo);
            }
            
            cs1.close();
            rs.close();
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * returns the location of the most viewed photo in the tag you pass in.
     * @param tag tag of the photo
     * @return location of the most viewed photo with a certain tag
     */
    public String photoGetTopPhotoInTag(String tag)
    {
        String location = ""; 
         try {
            CallableStatement cs1 = conn.prepareCall("call photoGetTopInTag(?)");
            ResultSet rs;
            cs1.setString(1,tag);
            
            rs = cs1.executeQuery();
            rs.next();
            location = rs.getString(1);
            
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return location;
    }
    /**
     * returns a photo object with all the information associated with it from the database
     * @param photoID photo id of the photo get information of
     * @return Photo object holding all the information associated with it in the database
     */
    public Photo photoGetPhotoValues(int photoID)
    {
        Photo photo = null;
         try {
            CallableStatement cs1 = conn.prepareCall("call photoGetValues(?)");
            ResultSet rs;
            cs1.setInt(1,photoID);
            
            rs = cs1.executeQuery();
            rs.next();
            photo = new Photo(rs.getInt(1), rs.getInt(2),rs.getString(4),rs.getString(5),rs.getTimestamp(6),
                        rs.getInt(7), null, rs.getString(8));
            
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photo;
    }
    /**
     * returns one string containing the first and last name(separated by a space between the two) of the member who uploaded the photo
     * @param photoID the id of the photo
     * @return first and last name of the member who uploaded the photo
     */
    public Member photoGetMemberNameFromPhotoID(int photoID)
    {
        Member artist = null;
        String name = "";
         try {
            CallableStatement cs1 = conn.prepareCall("call photoGetMemberName(?)");
            ResultSet rs;
            cs1.setInt(1, photoID);
            
            rs = cs1.executeQuery();
            rs.next();
            artist = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),null,false,'0',null,rs.getString(9));
            
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artist;
    }
    /**
     * Returns an arraylist of strings filled with all the tags associated with the 
     * @param photoID 
     * @return 
     */
    public ArrayList<String> photoGetTags(int photoID)
    {
        ArrayList<String> tags = new ArrayList();
         try {
            CallableStatement cs1 = conn.prepareCall("call photoGetTags(?)");
            ResultSet rs;
            cs1.setInt(1, photoID);
            rs = cs1.executeQuery();
            while(rs.next())
            {
                tags.add(rs.getString(1));
            }
            
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
            tags.add("");
        }
        return tags;
    }
    
    /**
     * returns a ArrayList of photos that matches the search criteria given
     * @param search
     * @return
     */
    public ArrayList<Photo> photoSearchBroker(String search)
    {
        ArrayList<Photo> photoList = new ArrayList();
         try {
            CallableStatement cs1 = conn.prepareCall("call photoSearch(?)");
            cs1.setString(1, search);
            ResultSet rs = cs1.executeQuery();
            while(rs.next())
            {
                Photo photo = new Photo(rs.getInt(1),rs.getInt(2),rs.getString(4),
                    rs.getString(5),rs.getTimestamp(6),rs.getInt(7),null,rs.getString(8));
                photoList.add(photo);
            }
            
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
            photoList=null;
        }
        return photoList;
    }
    
    /**
     * returns all the photo that belongs to a specific upgraded member
     * @param userId
     * @return
     */
    public ArrayList<Photo> photoSearchMemberBroker(int userId)
    {
        ArrayList<Photo> photoList = new ArrayList();
         try {
            CallableStatement cs1 = conn.prepareCall("call photoMemberSearch(?)");
            cs1.setInt(1, userId);
            ResultSet rs = cs1.executeQuery();
            while(rs.next())
            {
                Photo photo = new Photo(rs.getInt(1),rs.getInt(2),rs.getString(4),
                    rs.getString(5),rs.getTimestamp(6),rs.getInt(7),null,rs.getString(8));
                photoList.add(photo);
            }
            
            rs.close();
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
            photoList=null;
        }
        return photoList;
    }
    
    /**
     * get all the unique tags that are inside the database
     * @return
     */
    public String photoGetAllTagsBroker(){
        
        try {
            CallableStatement cs1 = conn.prepareCall("call photoGetAllTags()");
            
            ResultSet rs=null;
            
            rs = cs1.executeQuery();
            
            String tagList= "";
            
            while (rs.next()){
                tagList = tagList + rs.getString(1) +",";
            }
            
            cs1.close();
            rs.close();
            return tagList;
            
        } catch (SQLException ex) {
            Logger.getLogger(memberBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * increments the counter of a specific picture
     * @param idPhoto
     */
    public void photoIncrementCountBroker(int idPhoto)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call photoIncrementCount(?)");
            
            cs1.setInt(1, idPhoto);
            cs1.execute();
                       
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get the location of a photo given the photo id
     * @param idPhoto
     * @return
     */
    public String photoGetLocationBroker(int idPhoto)
    {
        String location = "";
        try {
            CallableStatement cs1 = conn.prepareCall("call photoGetLocation(?)");
            ResultSet rs=null;
            cs1.setInt(1, idPhoto);
            rs = cs1.executeQuery();
            rs.next();
            location = rs.getString(1);
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return location;
    }
    
    /**
     * update a photo with new values by using the photo id   
     * @param idPhoto
     * @param title
     * @param description
     * @param regularPrice
     * @param exclusivePrice
     */
    public void updatePhotoBroker(int idPhoto, String title, String description, int regularPrice, int exclusivePrice)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call updatePhoto(?,?,?,?,?)");
            
            cs1.setInt(1,idPhoto);
            cs1.setString(3,title);
            cs1.setString(2,description);
            cs1.setInt(4,regularPrice);
            cs1.setInt(5,exclusivePrice);
            cs1.execute();
            
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * updates the tags of a given photo id
     * @param idPhoto
     * @param tags
     * @param oldTagsList
     */
    public void updateTagsBroker(int idPhoto, String[] tags, String[] oldTagsList)
    {
        try {
            CallableStatement cs1 = conn.prepareCall("call updateTags(?,?,?)");
            
            for(int i = 0; i < tags.length; i++)
            {
                cs1.setInt(1, idPhoto);
                cs1.setString(2, tags[i]);
                cs1.setString(3, oldTagsList[i]);
                cs1.execute();
            }
            
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get the price of a photo given the photo id
     * @param idPhoto
     * @return
     */
    public int photoGetRegularPrice(int idPhoto)
    {
        int price = 0;
        try {
            CallableStatement cs1 = conn.prepareCall("call photoGetRegularPrice(?)");
            ResultSet rs=null;
            cs1.setInt(1, idPhoto);
            rs = cs1.executeQuery();
            rs.next();
            price = rs.getInt(1);
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }
    
    /**
     * get the exclusive price for a given photo id
     * @param idPhoto
     * @return
     */
    public int photoGetExclusivePrice(int idPhoto)
    {
        int price = 0;
        try {
            CallableStatement cs1 = conn.prepareCall("call photoGetExclusivePrice(?)");
            ResultSet rs=null;
            cs1.setInt(1, idPhoto);
            rs = cs1.executeQuery();
            rs.next();
            price = rs.getInt(1);
            cs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }
    
    
    
    /**
     * Closes the connection
     */
    public void close()
    {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhotoBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
