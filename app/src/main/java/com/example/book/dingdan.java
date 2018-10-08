package com.example.book;

import cn.bmob.v3.BmobObject;

/**
 * Created by THINK on 2017/11/12.
 */

public class dingdan extends BmobObject
{
    private String username;
    private String bookid;
    private String zongprice;
    private String salename;
    private Book dingdanbook;
    private String mydingdanbookstatus;
    private String bookname;
    private String bookprice;
    private String bookpicurl;
    private Number num;

    public Number getNum() {
        return num;
    }

    public void setNum(Number num) {
        this.num = num;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookprice() {
        return bookprice;
    }

    public void setBookprice(String bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookpicurl() {
        return bookpicurl;
    }

    public void setBookpicurl(String bookpicurl) {
        this.bookpicurl = bookpicurl;
    }

    public String getMydingdanbookstatus() {
        return mydingdanbookstatus;
    }

    public void setMydingdanbookstatus(String mydingdanbookstatus) {
        this.mydingdanbookstatus = mydingdanbookstatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getZongprice() {
        return zongprice;
    }

    public void setZongprice(String zongprice) {
        this.zongprice = zongprice;
    }

    public String getSalename() {
        return salename;
    }

    public void setSalename(String salename) {
        this.salename = salename;
    }

    public Book getDingdanbook() {
        return dingdanbook;
    }

    public void setDingdanbook(Book dingdanbook) {
        this.dingdanbook = dingdanbook;
    }

    @Override
    public String toString() {
        return "dingdan{" +
                "username='" + username + '\'' +
                ", bookid='" + bookid + '\'' +
                ", zongprice='" + zongprice + '\'' +
                ", salename='" + salename + '\'' +
                ", dingdanbook=" + dingdanbook +
                ", mydingdanbookstatus='" + mydingdanbookstatus + '\'' +
                '}';
    }
}
