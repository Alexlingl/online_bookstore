package com.book.dao;

import com.book.domain.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClassDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    private final static String QUERY_SPECIFY_CLASS="SELECT name FROM class_info where class_id = ?;";

    public ClassInfo querySpecifyClass(String classId){
        final ClassInfo classInfo=new ClassInfo();
        classInfo.setClassId(classId);
        jdbcTemplate.query(QUERY_SPECIFY_CLASS, new Object[]{classId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                classInfo.setName(resultSet.getString("name"));
            }
        });

        return classInfo;
    }

}
