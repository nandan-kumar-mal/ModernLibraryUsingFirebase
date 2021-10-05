package com.nandan.modernlibraryusingfirebase;

public class Transdata {
    String name, roll, sem, book, date;

    public Transdata(String name, String roll, String sem, String book, String date) {

        this.name = name;
        this.roll = roll;
        this.sem = sem;
        this.book = book;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
