package com.brevitaz.springexample.SpringStarting.dao;

import com.brevitaz.springexample.SpringStarting.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
//Repository annotation are used at dao implementation
public class StudentDaoImpl implements StudentDao {
    private static final String index = "students";
    private RestHighLevelClient client = EsRestClient.getClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Student> students = new ArrayList<>();

    public boolean save(Student student) {
        try {
            IndexRequest request = new IndexRequest(
                    index,
                    "doc",
                    student.getNo());
            String json = objectMapper.writeValueAsString(student);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            System.out.println(response.status());
            if (response.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        } catch (IndexNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Student> getData() {
        return students;
    }

    @Override
    public Student getById(String no) throws IOException {
        GetRequest getRequest = new GetRequest(
                index,
                "doc",
                no);

        GetResponse getResponse = client.get(getRequest);
        return objectMapper.readValue(getResponse.getSourceAsString(), Student.class);
    }


    public Student getByName(String name) throws IOException {
        GetRequest getRequest = new GetRequest(
                index,
                "doc",
                name);

        GetResponse getResponse = client.get(getRequest);
        for (Student s : students) {
            if ((s.getName()).equals(name)) {
                return objectMapper.readValue(getResponse.getSourceAsString(), Student.class);
            }
        }
        return null;
    }

    public boolean delete(String no) throws IOException {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(
                    index,
                    "doc",
                    no);

            DeleteResponse deleteResponse = client.delete(deleteRequest);

            if (deleteResponse.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        } catch (IndexNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UpdateRequest update(String no, Student student1) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(
                index,
                "doc",
                no);

        String json = objectMapper.writeValueAsString(student1);
        return updateRequest.doc(json, XContentType.JSON);

    }
}
