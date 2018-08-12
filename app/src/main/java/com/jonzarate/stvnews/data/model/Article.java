package com.jonzarate.stvnews.data.model;

import android.graphics.Bitmap;

import java.util.List;

public class Article {

    int id;
    String title;
    String subHeadline;
    List<ArticleImage> images;
    String contentHTML;

    String imageUrl;
    Bitmap image;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubHeadline() {
        return subHeadline;
    }

    public List<ArticleImage> getImages() {
        return images;
    }

    public String getContentHTML() {
        return contentHTML;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
