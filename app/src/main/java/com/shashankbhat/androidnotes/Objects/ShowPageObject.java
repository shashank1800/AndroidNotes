package com.shashankbhat.androidnotes.Objects;

public class ShowPageObject {

    private String titleText,contentUrl,rawDataUrl;

    public ShowPageObject(String titleText,String contentUrl,String rawDataUrl){
        this.titleText = titleText;
        this.contentUrl = contentUrl;
        this.rawDataUrl = rawDataUrl;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getRawDataUrl() {
        return rawDataUrl;
    }

    public void setRawDataUrl(String rawDataUrl) {
        this.rawDataUrl = rawDataUrl;
    }
}
