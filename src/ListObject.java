/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//class for the objects in jlist


import javax.swing.ImageIcon;

/**
 *
 * @author lokesh
 */
public class ListObject {
    ImageIcon ic;
    String title;
    String channelTitle;
    String description;
    String url;
    String thumbnail;
    String videoID;

    public ListObject(String title,String description,String channelTitle,String thumbnail,String videoID) {
        this.title=title;
        this.channelTitle=channelTitle;
        this.description=description;
        this.url=thumbnail;
        this.videoID=videoID;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public ImageIcon getIc() {
        return ic;
    }

    public void setIc(ImageIcon ic) {
        this.ic = ic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    
    
}


