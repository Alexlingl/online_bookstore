package com.book.web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = new Date();
        System.out.println(date);// new Date()为获取当前系统时间
    }
}
