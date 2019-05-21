package com.book.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args){
//        BigDecimal test = BigDecimal.valueOf(Long.valueOf("30.00"));
        BigDecimal bd=new BigDecimal("30.00");
        System.out.println(bd);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = new Date();
        System.out.println(date);// new Date()为获取当前系统时间
        System.out.println(new Date("20171101"));
    }
}
