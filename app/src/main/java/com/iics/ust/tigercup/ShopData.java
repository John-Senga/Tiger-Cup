package com.iics.ust.tigercup;

public class ShopData {
    public String Name;
    public String Category;
    public String Image;
    public String Latitude;
    public String Longitude;
    public ShopData next;

    public ShopData(String Name, String Category, String Image, String Latitude, String Longitude){
        this.Name = Name;
        this.Category = Category;
        this.Image = Image;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public void setNext(ShopData next) {
        this.next = next;
    }
}
