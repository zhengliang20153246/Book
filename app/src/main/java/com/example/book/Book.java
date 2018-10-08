package com.example.book;





import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by LI on 2017/11/10.
 */

public class Book extends BmobObject {
    private BmobFile pic1;
    private BmobFile pic2;
    private String bookname;
    private String zuozhe;
    private String chubanshe;
    private String fenlei;
    private int id;
    private String ISBN;
    private String jieshao;
    private String num;
    private String picurl1;
    private String picurl2;
    private Number price;
    private String school;
    private String status1;
    private String status2;
    private String username;
    private Date date;
    private String banben;
    private String gouwucheid;
    private String mydingdanbookstatus;

    public String getMydingdanbookstatus() {
        return mydingdanbookstatus;
    }

    public void setMydingdanbookstatus(String mydingdanbookstatus) {
        this.mydingdanbookstatus = mydingdanbookstatus;
    }

    public String getGouwucheid() {
        return gouwucheid;
    }

    public void setGouwucheid(String gouwucheid) {
        this.gouwucheid = gouwucheid;
    }

    public String getBanben() {
        return banben;
    }

    public void setBanben(String banben) {
        this.banben = banben;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public BmobFile getPic1() {
        return pic1;
    }

    public void setPic1(BmobFile pic1) {
        this.pic1 = pic1;
    }

    public BmobFile getPic2() {
        return pic2;
    }

    public void setPic2(BmobFile pic2) {
        this.pic2 = pic2;
    }

    public String getZuozhe() {
        return zuozhe;
    }

    public void setZuozhe(String zuozhe) {
        this.zuozhe = zuozhe;
    }

    public String getChubanshe() {
        return chubanshe;
    }

    public void setChubanshe(String chubanshe) {
        this.chubanshe = chubanshe;
    }

    public String getFenlei() {
        return fenlei;
    }

    public void setFenlei(String fenlei) {
        this.fenlei = fenlei;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPicurl1() {
        return picurl1;
    }

    public void setPicurl1(String picurl1) {
        this.picurl1 = picurl1;
    }

    public String getPicurl2() {
        return picurl2;
    }

    public void setPicurl2(String picurl2) {
        this.picurl2 = picurl2;
    }



    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Book{" +
                "pic1=" + pic1 +
                ", pic2=" + pic2 +
                ", bookname='" + bookname + '\'' +
                ", zuozhe='" + zuozhe + '\'' +
                ", chubanshe='" + chubanshe + '\'' +
                ", fenlei='" + fenlei + '\'' +
                ", id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", jieshao='" + jieshao + '\'' +
                ", num='" + num + '\'' +
                ", picurl1='" + picurl1 + '\'' +
                ", picurl2='" + picurl2 + '\'' +
                ", price=" + price +
                ", school='" + school + '\'' +
                ", status1='" + status1 + '\'' +
                ", status2='" + status2 + '\'' +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", banben='" + banben + '\'' +
                ", gouwucheid='" + gouwucheid + '\'' +
                ", mydingdanbookstatus='" + mydingdanbookstatus + '\'' +
                '}';
    }
}