package com.book.service;

import com.book.dao.ReaderInfoDao;
import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class ReaderInfoService {

    private ReaderInfoDao readerInfoDao;
    @Autowired
    public void setReaderInfoDao(ReaderInfoDao readerInfoDao) {
        this.readerInfoDao = readerInfoDao;
    }
    public ArrayList<ReaderInfo> readerInfos(){
        return readerInfoDao.getAllReaderInfo();
    }

    public boolean deleteReaderInfo(int readerId){
        return readerInfoDao.deleteReaderInfo(readerId)>0;
    }

    public ReaderInfo getReaderInfo(int readerId){
        return readerInfoDao.findReaderInfoByReaderId(readerId);
    }
    public boolean editReaderInfo(ReaderInfo readerInfo){
        return readerInfoDao.editReaderInfo(readerInfo)>0;
    }
    public boolean addReaderInfo(ReaderInfo readerInfo){
        return  readerInfoDao.addReaderInfo(readerInfo)>0;
    }
    //从date类型的数据中提取出年月日
    public boolean parseDate(ReaderInfo readerInfo){
        //从birth字段中单独提取出年月日的信息
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(readerInfo.getBirth());
        String[] dateList = dateStr.split("-");
        String year = dateList[0];
        String month = dateList[1];
        String day = dateList[2];
//        System.out.println(year+" "+month+" "+day);
        readerInfo.setYear(year);
        readerInfo.setMonth(month);
        readerInfo.setDay(day);
        return true;
    };
}
