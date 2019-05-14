package com.book.dao;

import com.book.domain.Book;
import com.book.domain.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class PublishDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //根据编号查找出版社信息
    private final static String GET_PUBLISH_SQL="SELECT * FROM publish_info where publish_id = ? ";
//    private final static String QUERY_PUBLISH_SQL="SELECT * FROM publish_info WHERE publish_id =  ?";

//    public ArrayList<Publish> queryPublish(int PublishId){
//        final ArrayList<Publish> publishs=new ArrayList<Publish>();
//        jdbcTemplate.query(QUERY_PUBLISH_SQL, new Object[]{PublishId}, new RowCallbackHandler() {
//            public void processRow(ResultSet resultSet) throws SQLException {
//
//                resultSet.beforeFirst();
//                while (resultSet.next()){
//                    Publish publish = new Publish();
//                    publish.setPublishName(resultSet.getString("publish_name"));
//                    publish.setAddress(resultSet.getString("address"));
//                    publish.setContacter(resultSet.getString("contacter"));
//                    publish.setEmail(resultSet.getString("email"));
//                    publish.setPhone(resultSet.getString("phone"));
//                    publishs.add(publish);
//                }
//            }
//        });
//        return publishs;
//    }

    public Publish getPublish(int publishId){
        final Publish publish=new Publish();
        jdbcTemplate.query(GET_PUBLISH_SQL, new Object[]{publishId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                publish.setPublishName(resultSet.getString("publish_name"));
                publish.setAddress(resultSet.getString("address"));
                publish.setContacter(resultSet.getString("contacter"));
                publish.setEmail(resultSet.getString("email"));
                publish.setPhone(resultSet.getString("phone"));
            }
        });
        return publish;
    }

}
