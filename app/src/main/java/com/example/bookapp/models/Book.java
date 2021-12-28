package com.example.bookapp.models;

public class Book {
    private int id;
    private String bookName;
    private int relaesYesrs;
    private String authoName;
    private int pageNumber;
    private byte[] image;

    public Book(String bookName, int relaesYesrs, String authoName, int pageNumber) {
        this.bookName = bookName;
        this.relaesYesrs = relaesYesrs;
        this.authoName = authoName;
        this.pageNumber = pageNumber;
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
