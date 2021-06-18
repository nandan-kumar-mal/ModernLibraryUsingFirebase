package com.nandan.modernlibraryusingfirebase;

public class AddBookHelperClass {
    String title, author, category, edition;

    public AddBookHelperClass() {

    }

    public AddBookHelperClass(String title, String author, String category, String edition) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.edition = edition;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
