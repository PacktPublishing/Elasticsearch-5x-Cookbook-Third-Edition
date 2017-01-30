package com.packtpub;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        RestClient client = RestClient.builder(
                new HttpHost("localhost", 9200, "http")).build();

        try {
            Response response = client.performRequest("GET", "/test-index/test-type/1");

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + response.getStatusLine());
            } else {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);
                System.out.println(responseBody);
            }

        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
