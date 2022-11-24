package com.example.ecommerces.Model;

public class ExploreMenu {
    String  type,img_url;

    public ExploreMenu() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ExploreMenu(String type, String img_url) {
        this.type = type;
        this.img_url = img_url;
    }
}
