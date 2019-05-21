package com.book.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Sell implements Serializable {
    private int serialNumber;
    private Date date;
    private int readerId;
    private BigDecimal price;
    private long bookId;
    private int number;
    private int state;

    public void setSerialNumber(int serialNumber){
        this.serialNumber = serialNumber;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setReaderId(int readerId){
        this.readerId = readerId;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public void setBookId(long bookId){
        this.bookId = bookId;
    }

    public void setNumber(int number){ this.number = number;}

    public void setState(int state){this.state = state;}

    public int getSerialNumber() {
        return serialNumber;
    }

    public Date getDate() {
        return date;
    }

    public int getReaderId() {
        return readerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getBookId() {
        return bookId;
    }

    public int getState() {
        return state;
    }

    public int getNumber(){return number;}

}
