/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author sebaslab
 */
public class Photo {
    private int idPhoto;
    private int idMember;
    private String title;
    private String description;
    private Timestamp uploadDate;
    private int viewCount;
    private ArrayList<Tag> tagList;
    private String locationPhoto;

    /**
     *
     */
    public Photo() {
    }
    /**
     * 
     * @param idPhoto
     * @param idMember
     * @param title
     * @param description
     * @param uploadDate
     * @param viewCount
     * @param tagList
     * @param locationPhoto 
     */
    public Photo(int idPhoto, int idMember, String title, String description, Timestamp uploadDate, int viewCount, ArrayList<Tag> tagList, String locationPhoto) {
        this.idPhoto = idPhoto;
        this.idMember = idMember;
        this.title = title;
        this.description = description;
        this.uploadDate = uploadDate;
        this.viewCount = viewCount;
        this.tagList = tagList;
        this.locationPhoto = locationPhoto;
    }

    /**
     *
     * @return
     */
    public int getIdPhoto() {
        return idPhoto;
    }

    /**
     *
     * @param idPhoto
     */
    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
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
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    /**
     *
     * @param uploadDate
     */
    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     *
     * @return
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     *
     * @param viewCount
     */
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    /**
     *
     * @return
     */
    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    /**
     *
     * @param tagList
     */
    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    /**
     *
     * @return
     */
    public String getLocationPhoto() {
        return locationPhoto;
    }

    /**
     *
     * @param locationPhoto
     */
    public void setLocationPhoto(String locationPhoto) {
        this.locationPhoto = locationPhoto;
    }

    @Override
    public String toString() {
        return "Photo{" + "idPhoto=" + idPhoto + ", idMember=" + idMember + ", title=" + title + ", description=" + description + ", uploadDate=" + uploadDate + ", viewCount=" + viewCount + ", tagList=" + tagList + ", locationPhoto=" + locationPhoto + '}';
    }
}
