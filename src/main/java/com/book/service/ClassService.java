package com.book.service;

import com.book.dao.ClassDao;
import com.book.domain.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    private ClassDao classDao;

    @Autowired
    public void setClassDao(ClassDao classDao){ this.classDao = classDao; }

    public ClassInfo querySpecifyClass(String classId){
        return classDao.querySpecifyClass(classId);
    }

}
