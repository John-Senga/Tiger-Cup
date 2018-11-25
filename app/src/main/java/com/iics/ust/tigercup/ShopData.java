package com.iics.ust.tigercup;

public class ShopData {
    public String Id,Name,Category,Image,Latitude,Longitude;
    public ShopData next;

    public ShopData(String Id, String Name, String Category, String Image, String Latitude, String Longitude){
        this.Id = Id;
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
