package com.tommyputranto.photoiak;

/**
 * Created by tommy on 4/24/16.
 */
public class GalleryItem {
    private String mCaption;
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return mCaption;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }
}
