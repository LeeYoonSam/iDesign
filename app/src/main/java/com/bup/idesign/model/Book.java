package com.bup.idesign.model;

/**
 * Created by Albert-IM on 14/02/2017.
 */

public class Book {
    public String _id;
    public String title;
    public String author;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
