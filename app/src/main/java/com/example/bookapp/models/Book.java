package com.example.bookapp.models;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String bookName;
    private int relaesYesrs;
    private String authoName;
    private int pageNumber;
    private byte[] image;
    private String CategoryName;
    private boolean favourite;

    public Book(String bookName, int relaesYesrs, String authoName, int pageNumber, String categoryName, boolean favourite) {
        this.bookName = bookName;
        this.relaesYesrs = relaesYesrs;
        this.authoName = authoName;
        this.pageNumber = pageNumber;
        CategoryName = categoryName;
        this.favourite = favourite;
    }

    public Book(String bookName, int relaesYesrs, String authoName, int pageNumber, String categoryName) {
        this.bookName = bookName;
        this.relaesYesrs = relaesYesrs;
        this.authoName = authoName;
        this.pageNumber = pageNumber;
        CategoryName = categoryName;
    }

    public Book() {
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getRelaesYesrs() {
        return relaesYesrs;
    }

    public void setRelaesYesrs(int relaesYesrs) {
        this.relaesYesrs = relaesYesrs;
    }

    public String getAuthoName() {
        return authoName;
    }

    public void setAuthoName(String authoName) {
        this.authoName = authoName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
