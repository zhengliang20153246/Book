package com.example.book;

import cn.bmob.v3.BmobObject;

/**
 * Created by THINK on 2017/11/12.
 */

public class Gouwuche extends BmobObject
{
    private String username;
    private String bookid;

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
}
