package com.nandan.modernlibraryusingfirebase;

public class Transdata {
    String name, roll, year, book, date;

    public Transdata(String name, String roll, String year, String book, String date) {

        this.name = name;
        this.roll = roll;
        this.year = year;
        this.book = book;
        this.date = date;
    }

    public Transdata() {}
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
