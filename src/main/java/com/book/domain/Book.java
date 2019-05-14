package com.book.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Book implements Serializable{

    private long bookId;
    private String name;
    private String author;
    private String translator;
    private String publish;
    private int publishId;
    private String isbn;
    private String introduction;
    private String language;
    private BigDecimal price;
    //bead类的语法格式，虽然数据库中是vip_price，但是生成bean类后会变成vipPrice
    private BigDecimal vipPrice;
    private Date pubdate;
    private int classId;
    private int pressmark;
    private int state;

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setPublishId(int publishId) {
        this.publishId = publishId;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public void setPressmark(int pressmark) {
        this.pressmark = pressmark;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public long getBookId() {
        return bookId;
    }

    public int getClassId() {
        return classId;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public String getAuthor() {
        return author;
    }

    public String getTranslator() {
        return translator;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getPressmark() {
        return pressmark;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLanguage() {
        return language;
    }

    public int getState() {
        return state;
    }

    public String getPublish() {
        return publish;
    }

    public int getPublishId() {
        return publishId;
    }

    @Override
    public String toString() {
        return "这本书的信息为"+pressmark+pubdate+bookId+name+author+publish+isbn+introduction+language+price+classId+state;
    }
}
