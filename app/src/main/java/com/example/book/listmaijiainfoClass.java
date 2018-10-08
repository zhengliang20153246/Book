package com.example.book;

/**
 * Created by LI on 2017/10/29.
 */

public class listmaijiainfoClass {

    private String maijiainfo;
    private String maijiashouji;
    private String maijiaweixin;
    private String maijiaQQ;
    private int shuinfo;

    public listmaijiainfoClass(String maijiainfo,String maijiashouji,String maijiaweixin,String maijiaQQ,int shuinfo)
    {
        this.maijiainfo=maijiainfo;
        this.maijiashouji=maijiashouji;
        this.maijiaweixin=maijiaweixin;
        this.shuinfo=shuinfo;
        this.maijiaQQ=maijiaQQ;
    }

    public String getMaijiainfo() {
        return maijiainfo;
    }

    public String getMaijiaQQ() {
        return maijiaQQ;
    }

    public String getMaijiashouji() {
        return maijiashouji;
    }

    public String getMaijiaweixin() {
        return maijiaweixin;
    }

    public int getShuinfo() {
        return shuinfo;
    }
}
