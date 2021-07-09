package com.nandan.modernlibraryusingfirebase;

public class Contact {
    String title, icon, author, availability;

    public Contact(){}

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getAvailability() {
        return availability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Contact(String title, String icon, String author, String availability) {
        this.title = title;
        this.icon = icon;
        this.author = author;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIcon() {
        return icon;
    }




}
