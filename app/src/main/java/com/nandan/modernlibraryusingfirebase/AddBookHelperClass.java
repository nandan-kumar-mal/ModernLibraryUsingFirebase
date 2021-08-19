package com.nandan.modernlibraryusingfirebase;

public class AddBookHelperClass {
    String title;
    String author;
    String edition;
    String category;
    String icon;
    String copies;
    String topic;

    public AddBookHelperClass() {

    }



    public AddBookHelperClass(String title, String author, String category, String edition, String icon, String copies,String topic) {
        this.title = title;
        this.author = author;
       this.category = category;
        this.edition = edition;
        this.icon = icon;
        this.copies = copies;
        this.topic=topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCopies() {   return copies;  }

    public void setCopies(String copies) {   this.copies = copies; }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
