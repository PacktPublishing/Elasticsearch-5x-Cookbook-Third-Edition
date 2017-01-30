package com.packtpub;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class QueryCreation {

    public static void main(String[] args) throws UnknownHostException {
        String index = "mytest";
        String type = "mytype";
        Client client = NativeClient.createTransportClient();
        IndicesOperations io = new IndicesOperations(client);
        if (io.checkIndexExists(index))
            io.deleteIndex(index);

        try {
            client.admin().indices().prepareCreate(index)
                    .addMapping(type, XContentFactory.jsonBuilder()
                            .startObject()
                            .startObject(type)
                            .startObject("properties")
                            .startObject("text").field("type", "integer").field("store", "yes").endObject()
                            .startObject("number1").field("type", "integer").field("store", "yes").endObject()
                            .startObject("number2").field("type", "integer").field("store", "yes").endObject()
                            .endObject()
                            .endObject()
                            .endObject())
                    .execute().actionGet();
        } catch (IOException e) {
            System.out.println("Unable to create mapping");
        }

        BulkRequestBuilder bulker = client.prepareBulk();
        for (Integer i = 1; i < 1000; i++) {
            bulker.add(client.prepareIndex(index, type, i.toString()).setSource("text", i.toString(), "number1", i + 1, "number2", i % 2));
        }
        bulker.execute().actionGet();

        client.admin().indices().prepareRefresh(index).execute().actionGet();

        TermQueryBuilder filter = termQuery("number2", 1);
        RangeQueryBuilder range = rangeQuery("number1").gt(500);
        BoolQueryBuilder query = boolQuery().must(range).filter(filter);

        SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(query).execute().actionGet();
        System.out.println("Matched records of elements: " + response.getHits().getTotalHits());

        io.deleteIndex(index);

        //we need to close the client to free resources
        client.close();

    }
}
