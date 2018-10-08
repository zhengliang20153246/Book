package com.example.book;

/**
 * Created by LI on 2017/10/28.
 */

public class listrexiaoClass {
    private String name;
    private String date;
    private String price;
    private int image1;

    public listrexiaoClass(String name, String price, String date, int image1)
    {
        this.name=name;
        this.price=price;
        this.date=date;
        this.image1=image1;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public int getImage1() {
        return image1;
    }
    public String getName() {
        return name;
    }
    public String getPrice() { return price;}
    public String getDate() { return date;}

}

