/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import javax.swing.Icon;

/**
 *
 * @author soumy
 */
public class listData {
    private Icon thumbnail;
    private String videoName;
    private String likesCount;
    private String dislikeCount;

    public listData(Icon thumbnail, String videoName, String likesCount, String dislikeCount) {
        this.thumbnail = thumbnail;
        this.videoName = videoName;
        this.likesCount = likesCount;
        this.dislikeCount = dislikeCount;
    }

    public Icon getThumbnail() {
        return thumbnail;
    }
 
    public void setThumbnail(Icon thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(String dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
     
    
}
