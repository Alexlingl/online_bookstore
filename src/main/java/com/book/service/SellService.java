package com.book.service;

import com.book.dao.SellDao;
import com.book.domain.Book;
import com.book.domain.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class SellService {
    private SellDao sellDao;

    @Autowired
    public void setSellDao(SellDao sellDao) {
        this.sellDao = sellDao;
    }

    public ArrayList<Sell> mySellList(int readerId){
        return sellDao.myBuyList(readerId);
    }

    public boolean addSell(Sell sell){
        return sellDao.addBuy(sell)>0;
    }

    public ArrayList<Sell> sellList(){
        return sellDao.sellList();
    }

    public Sell getSell(int serialNumber){
        return sellDao.getSell(serialNumber);
    }

    public boolean editSell(Sell sell){return sellDao.editSell(sell)>0;}

    public boolean deleteSell(int serialNumber){return sellDao.deleteSell(serialNumber)>0;}

    @Transactional(rollbackFor = {Exception.class})
    public boolean editAndAdd(BookService bookService,Book book,Sell sell){
        boolean bookSucc = bookService.editBook(book);
        boolean sellSucc = false;
        if(bookSucc){
//            int i=2/0;
            sellSucc = addSell(sell);
        }
        return sellSucc;
    }

}
