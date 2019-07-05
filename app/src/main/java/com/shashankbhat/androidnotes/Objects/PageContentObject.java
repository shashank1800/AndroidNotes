package com.shashankbhat.androidnotes.Objects;
public class PageContentObject {
    private String heading,url;

    public PageContentObject(String heading,String url){
        this.heading = heading;
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
