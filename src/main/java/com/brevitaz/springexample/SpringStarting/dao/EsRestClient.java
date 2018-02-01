package com.brevitaz.springexample.SpringStarting.dao;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsRestClient {
    public static RestHighLevelClient client;

    public EsRestClient() {
    }

    public static RestHighLevelClient getClient() {
        if (client == null) {
            client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            return client;
        } else {
            return client;
        }
    }
}
