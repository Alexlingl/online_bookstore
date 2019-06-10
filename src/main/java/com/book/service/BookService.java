package com.book.service;

import com.book.dao.BookDao;
import com.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public ArrayList<Book> queryBook(String searchWord){
        return  bookDao.queryBook(searchWord);
    }

    public Book getOneBook(long bookId){
        return bookDao.getBook(bookId);
    }

    public ArrayList<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public int deleteBook(long bookId){
        return bookDao.deleteBook(bookId);
    }

    public boolean matchBook(String searchWord){
        return bookDao.matchBook(searchWord)>0;
    }

    public boolean addBook(Book book){
        return bookDao.addBook(book)>0;
    }

    public Book getBook(Long bookId){
        Book book=bookDao.getBook(bookId);
        return book;
    }
    public boolean editBook(Book book){
        return bookDao.editBook(book)>0;
    }
    //从date类型的数据中提取出年月日
    public boolean parseDate(Book book){
        //从birth字段中单独提取出年月日的信息
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(book.getPubdate());
        String[] dateList = dateStr.split("-");
        String year = dateList[0];
        String month = dateList[1];
        String day = dateList[2];
//        System.out.println(year+" "+month+" "+day);
        book.setYear(year);
        book.setMonth(month);
        book.setDay(day);
        return true;
    };

}
