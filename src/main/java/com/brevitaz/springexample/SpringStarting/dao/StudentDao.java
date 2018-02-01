package com.brevitaz.springexample.SpringStarting.dao;

import com.brevitaz.springexample.SpringStarting.model.Student;
import org.elasticsearch.action.update.UpdateRequest;

import java.io.IOException;
import java.util.List;

public interface StudentDao {
    boolean save(Student student);

    List<Student> getData();

    Student getById(String no) throws IOException;

    Student getByName(String name) throws IOException;

    //     Student getByCity(String city);
    boolean delete(String no) throws IOException;

    UpdateRequest update(String no, Student student) throws IOException;
}
