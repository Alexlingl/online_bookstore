package com.book.service;

import com.book.dao.SellDao;
import com.book.domain.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
