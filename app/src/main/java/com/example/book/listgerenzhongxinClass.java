package com.example.book;

/**
 * Created by LI on 2017/10/26.
 */

public class listgerenzhongxinClass {
    private String name;
    private int imageid;

    public listgerenzhongxinClass(String name,int imageid)
    {
        this.name=name;
        this.imageid=imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageid() {
        return imageid;
    }

    public String getName() {
        return name;
    }
}
