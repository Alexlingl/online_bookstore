package com.book.service;

import com.book.dao.PublishDao;
import com.book.domain.Book;
import com.book.domain.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PublishService {
    private PublishDao publishDao;

    @Autowired
    public void setPublishDao(PublishDao publishDao) {
        this.publishDao = publishDao;
    }


    public Publish getPublish(int publishId){
        Publish pulish = publishDao.getPublish(publishId);
        return pulish;
    }

}
