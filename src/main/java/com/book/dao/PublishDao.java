package com.book.dao;


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
    private final static String ALL_PUBLISH_SQL="SELECT * FROM publish_info";
    private final static String DELETE_PUBLISH_SQL="DELETE FROM publish_info where publish_id = ?";
    private final static String EDIT_PUBLISH_SQL="UPDATE publish_info set publish_name = ?, phone = ?, contacter = ?, email = ?, address = ? where publish_id = ?";
    private final static String ADD_PIBLISH_SQL="INSERT INTO publish_info VALUES (NULL ,?,?,?,?,?)";

    public Publish getPublish(int publishId){
        final Publish publish=new Publish();
        jdbcTemplate.query(GET_PUBLISH_SQL, new Object[]{publishId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                publish.setPublishId(resultSet.getInt("publish_id"));
                publish.setPublishName(resultSet.getString("publish_name"));
                publish.setAddress(resultSet.getString("address"));
                publish.setContacter(resultSet.getString("contacter"));
                publish.setEmail(resultSet.getString("email"));
                publish.setPhone(resultSet.getString("phone"));
            }
        });
        return publish;
    }

    public ArrayList<Publish> getAllPublish(){
        final ArrayList<Publish> publishes=new ArrayList<Publish>();
        jdbcTemplate.query(ALL_PUBLISH_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Publish publish = new Publish();
                    publish.setPhone(resultSet.getString("phone"));
                    publish.setEmail(resultSet.getString("email"));
                    publish.setContacter(resultSet.getString("contacter"));
                    publish.setAddress(resultSet.getString("address"));
                    publish.setPublishName(resultSet.getString("publish_name"));
                    publish.setPublishId(resultSet.getInt("publish_id"));
                    publishes.add(publish);
                }
            }
        });
        return publishes;
    }

    public int deletePublish(int publishId){
        return jdbcTemplate.update(DELETE_PUBLISH_SQL,publishId);
    }

    public int editPublish(Publish publish){
        int publishId = publish.getPublishId();
        String publishName = publish.getPublishName();
        String phone = publish.getPhone();
        String contacter = publish.getContacter();
        String email = publish.getEmail();
        String address = publish.getAddress();

        return jdbcTemplate.update(EDIT_PUBLISH_SQL,new Object[]{publishName, phone, contacter, email, address, publishId});
    }

    public int addPublish(Publish publish){
        String publishName = publish.getPublishName();
        String contacter = publish.getContacter();
        String email = publish.getEmail();
        String address = publish.getAddress();
        String phone = publish.getPhone();

        return jdbcTemplate.update(ADD_PIBLISH_SQL,new Object[]{publishName,phone,contacter,email,address});
    }

}
