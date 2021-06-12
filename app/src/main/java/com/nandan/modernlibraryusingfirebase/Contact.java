package com.nandan.modernlibraryusingfirebase;

public class Contact {
    private String title;
    private String author;
    private Boolean availability;
    private String imageUrl;

    public Contact(String title, String author, Boolean availability, String imageUrl) {
        this.title = title;
        this.author = author;
        this.availability = availability;
        this.imageUrl = imageUrl;
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

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", availability=" + availability +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
