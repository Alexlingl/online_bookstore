package com.book.dao;

import com.book.domain.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class SellDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String MY_BUY_LIST_SQL="SELECT * FROM sell_info WHERE reader_id = ? ";
    private final static String ADD_BUY_SQL="INSERT INTO sell_info (date,reader_id,price,book_id,state) VALUES(?,?,?,?,?) ";
    private final static String SELL_LIST_SQL="SELECT * FROM sell_info";

    public ArrayList<Sell> myBuyList(int readerId){
        final ArrayList<Sell> list=new ArrayList<Sell>();
        jdbcTemplate.query(MY_BUY_LIST_SQL, new Object[]{readerId},new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Sell sell=new Sell();
                    sell.setSerialNumber(resultSet.getInt("serial_number"));
                    sell.setBookId(resultSet.getLong("book_id"));
                    sell.setDate(resultSet.getDate("date"));
                    sell.setPrice(resultSet.getBigDecimal("price"));
                    sell.setReaderId(resultSet.getInt("reader_id"));
                    sell.setState(resultSet.getInt("state"));
                    list.add(sell);
                }
            }
        });
        return list;
    }

    public int addBuy(Sell sell){
        Date date = sell.getDate();
        int readerId = sell.getReaderId();
        BigDecimal price = sell.getPrice();
        long bookId = sell.getBookId();
        int state = sell.getState();

        System.out.println("date="+date);
        return jdbcTemplate.update(ADD_BUY_SQL,new Object[]{date,readerId,price,bookId,state});
    }

    public ArrayList<Sell> sellList(){
        final ArrayList<Sell> list=new ArrayList<Sell>();
        jdbcTemplate.query(SELL_LIST_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Sell sell=new Sell();
                    sell.setSerialNumber(resultSet.getInt("serial_number"));
                    sell.setBookId(resultSet.getLong("book_id"));
                    sell.setPrice(resultSet.getBigDecimal("price"));
                    sell.setDate(resultSet.getDate("date"));
                    sell.setReaderId(resultSet.getInt("reader_id"));
                    sell.setState(resultSet.getInt("state"));
                    list.add(sell);
                }
            }
        });
        return list;
    }

}
