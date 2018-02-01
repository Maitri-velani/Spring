package com.brevitaz.springexample.SpringStarting.controller;

import com.brevitaz.springexample.SpringStarting.dao.StudentDao;
import com.brevitaz.springexample.SpringStarting.model.Student;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@RestController
//restcontroller are used for doing whole http request and it will used in Controller part.
//it can have controller and requestbody annotation.
@RequestMapping("/students")
public class StudentController extends HttpServlet {

    @Autowired
    StudentDao dao;


    @RequestMapping(method = {RequestMethod.POST})
    public boolean save(@RequestBody Student student) {
        return dao.save(student);
    }


    @RequestMapping(method = {RequestMethod.GET})
    public List<Student> getData() {
        return dao.getData();
    }

    @RequestMapping(value = "{no}", method = {RequestMethod.GET})
    public Student getById(@PathVariable String no) throws IOException {
        try {
            return dao.getById(no);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/name={name}", method = {RequestMethod.GET})
    public Student getByName(@PathVariable String name) throws IOException {
        try {
            return dao.getByName(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "{no}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String no) throws IOException {
        return dao.delete(no);
    }

    @RequestMapping(value = "update/no={no}", method = {RequestMethod.PUT})
    public UpdateRequest updateData(@PathVariable String no, @RequestBody Student student1) throws IOException {
        //dao.update(getServletContext().getInitParameter("index"), student1);
        return dao.update(no, student1);

    }
}
