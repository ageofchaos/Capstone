/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import brokers.PhotoBroker;
import domains.Member;
import domains.Photo;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author 633630
 */
@Stateless
@LocalBean
public class PhotoFunctions {

    /**
     *
     */
    public PhotoFunctions() {
                
    }
    
    /**
     * Adds a new photo to the database
     * @param idMember
     * @param idLicensing
     * @param title
     * @param description
     * @param location
     */
    public void photoAdd(int idMember, int idLicensing, String title, 
                        String description, String location)
    {
        PhotoBroker pb = new PhotoBroker();
        pb.photoAddBroker(idMember, idLicensing, title, description, location);
        pb.close();
    }

    /**
     *  Adds a new photo to the database
     * @param idMember
     * @param idLicensing
     * @param title
     * @param description
     * @param location
     * @param price
     * @param exclusivePrice
     */
    public void photoAdd(int idMember, int idLicensing, String title, 
                        String description, String location, int price, int exclusivePrice)
    {
        PhotoBroker pb = new PhotoBroker();
        pb.photoAddBroker(idMember, idLicensing, title, description, location, price, exclusivePrice);
        pb.close();
    }
    
    /**
     * Deletes the photo from database 
     * @param idPhoto uses the photo id to delete
     */
    public void photoDelete(int idPhoto)
    {
        PhotoBroker pb = new PhotoBroker();
        pb.photoDeleteBroker(idPhoto);
        pb.close();
    }
    
    /**
     * adds a new tag to a given picture
     * @param idPhoto
     * @param tags
     */
    public void addTag(int idPhoto, String[] tags)
    {
        PhotoBroker pb = new PhotoBroker();
        pb.photoAddTagsBroker(idPhoto, tags);
        pb.close();
    }
    
    /**
     * retrieves the photo id with a location
     * @param location
     * @return photo id
     */
    public int getId(String location)
    {
        PhotoBroker pb = new PhotoBroker();
        return pb.photoGetIdBroker(location);
    }
    
    /**
     * returns all of the photo that matches the given search parameter
     * @param searchValue what is to be searched
     * @return a ArrayList containing all the photo
     */
    public ArrayList searchPhoto(String[] searchValue){
        PhotoBroker pb = new PhotoBroker();
        ArrayList<Photo> photoList = new ArrayList<Photo>();
        for(int i=0; i<searchValue.length; i++)
        {
            photoList.addAll(pb.photoSearchBroker(searchValue[i]));
        }
        for(int i=photoList.size()-1;i>=0;i--){
            for(int x=photoList.size()-1;x>=0;x--){
                if(photoList.size()>i){
                    if(x!=i){
                        if(photoList.get(i).getIdPhoto() == photoList.get(x).getIdPhoto()){
                            photoList.remove(i);
                        }
                    }
                } 
            }
        }
        pb.close();
        return photoList;
    }
    
    /**
     * gets a ArrayList of photos for a specific user
     * @param userId
     * @return
     */
    public ArrayList searchPhotoMember(int userId){
        PhotoBroker pb = new PhotoBroker();
        ArrayList<Photo> photoList = new ArrayList<Photo>();
        photoList = pb.photoSearchMemberBroker(userId);
        pb.close();
        return photoList;
    }
    
    /**
     * gets all the unique tag in the database
     * @return
     */
    public String[] getTagList(){
        PhotoBroker pb = new PhotoBroker();
        String oldTagList = pb.photoGetAllTagsBroker();
        String[] tagList = oldTagList.split(",");
        pb.close();
        return tagList;
    }
    
    /**
     * increment the view count for a specific photo
     * @param picId
     */
    public void incrementCount(int picId){
        PhotoBroker pb = new PhotoBroker();
        
        pb.photoIncrementCountBroker(picId);
        pb.close();
    }
    
    /**
     * get the location of a specific photo using the photo id
     * @param photoId
     * @return
     */
    public String getLocation(int photoId)
    {
        PhotoBroker pb = new PhotoBroker();
        String location = pb.photoGetLocationBroker(photoId);
        pb.close();
        return location;
    }
    
    /**
     * get the artist of a photo using the photo id
     * @param photoId
     * @return
     */
    public String getArtist(int photoId)
    {
        PhotoBroker pb = new PhotoBroker();
        Member artist = pb.photoGetMemberNameFromPhotoID(photoId);
        String artistName = artist.getEmail();
        pb.close();
        return artistName;
    }
    
    /**
     * update the tags for a specific photo
     * @param idPhoto
     * @param tags
     * @param oldTagsList
     */
    public void updateTags(int idPhoto, String[] tags, String[] oldTagsList)
    {
        PhotoBroker pb = new PhotoBroker();

        pb.updateTagsBroker(idPhoto, tags, oldTagsList);
        pb.close();
    }
    
    /**
     * update the photo with the new values
     * @param idPhoto
     * @param description
     * @param title
     * @param regularPrice
     * @param exclusivePrice
     */
    public void updatePhoto(int idPhoto, String description, String title, int regularPrice, int exclusivePrice)
    {
        PhotoBroker pb = new PhotoBroker();

        pb.updatePhotoBroker(idPhoto, description, title, regularPrice, exclusivePrice);
        pb.close();
    }
    
    /**
     * get the price for the given photo id
     * @param idPhoto
     * @return
     */
    public int getRegularPrice(int idPhoto)
    {
        PhotoBroker pb = new PhotoBroker();

        int price = pb.photoGetRegularPrice(idPhoto);
        pb.close();
        return price;
    }
    
    /**
     * get the exclusive price for a photo 
     * @param idPhoto
     * @return
     */
    public int getExclusivePrice(int idPhoto)
    {
        PhotoBroker pb = new PhotoBroker();

        int price = pb.photoGetExclusivePrice(idPhoto);
        pb.close();
        return price;
    }
}
