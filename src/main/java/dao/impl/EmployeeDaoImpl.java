package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.EsRestClient;
import model.Employee;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EmployeeDaoImpl{

    private RestHighLevelClient client = EsRestClient.getClient();
    private ObjectMapper objectMapper= new ObjectMapper();

    public Boolean insert(String index, Employee employee) throws IOException {
        try {
            IndexRequest request = new IndexRequest(
                        index,
                        "doc",
                        employee.getId());
            String json=objectMapper.writeValueAsString(employee);
            request.source(json, XContentType.JSON);
            IndexResponse response= client.index(request);
            if(response.status() == RestStatus.CREATED){
                return true;
            }else{
                return false;
            }
        }
        catch (IndexNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public UpdateResponse update(String index, Employee employee) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(
                index,
                "doc",
                employee.getId());

        String json = objectMapper.writeValueAsString(employee);
        updateRequest.doc(json, XContentType.JSON);
        return client.update(updateRequest);
    }

    public DeleteResponse delete(String index,Employee employee) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(
                index,
                "doc",
                employee.getId());

        return client.delete(deleteRequest);
    }

    public Employee get(String id,String index) throws IOException {
        GetRequest getRequest = new GetRequest(
                index,
                "doc",
                id);

        GetResponse getResponse= EsRestClient.getClient().get(getRequest);
        return objectMapper.readValue(getResponse.getSourceAsString(), Employee.class);
    }

    public Employee searchByCity(String city, String index) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.termQuery("city", city));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);

        SearchHit[] hits = searchResponse.getHits().getHits();

        objectMapper=new ObjectMapper();
        Employee employee1 = null;
        for (SearchHit hit:hits)
        {
            employee1=objectMapper.readValue(hit.getSourceAsString(),Employee.class);
         //   printWriter.println(employee1);
        }
        return employee1;

    }
}
