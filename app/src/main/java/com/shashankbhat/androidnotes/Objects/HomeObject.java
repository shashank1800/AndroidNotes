package com.shashankbhat.androidnotes.Objects;

public class HomeObject {
    private String heading,url,iconUrl,background;

    public HomeObject(String heading,String url,String iconUrl,String background){
        this.heading = heading;
        this.url = url;
        this.iconUrl = iconUrl;
        this.background = background;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
