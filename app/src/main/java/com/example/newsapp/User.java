package com.example.newsapp;

public class User {


    String pfurl,title,link;

    public String getPfurl() {
        return pfurl;
    }

    public void setPfurl(String pfurl) {
        this.pfurl = pfurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User(String pfurl, String title, String link) {
        this.pfurl = pfurl;
        this.title = title;
        this.link = link;
    }

    public User() {
    }
}
