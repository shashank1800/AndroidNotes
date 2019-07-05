package com.shashankbhat.androidnotes.Objects;

public class HomeObject {
    private String name,url;

    public HomeObject(String name,String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
