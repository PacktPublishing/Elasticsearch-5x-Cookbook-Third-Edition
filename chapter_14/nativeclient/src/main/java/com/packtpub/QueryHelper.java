package com.packtpub;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.Priority;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;

public class QueryHelper {
    private final Client client;
    private final IndicesOperations io;

    public QueryHelper() throws UnknownHostException {
        this.client = NativeClient.createTransportClient();
        io = new IndicesOperations(client);
    }

    private String[] tags = new String[]{"nice", "cool", "bad", "amazing"};

    private String getTag() {
        return tags[new Random().nextInt(tags.length)];
    }

    public void populateData(String index, String type) {
        if (io.checkIndexExists(index))
            io.deleteIndex(index);

        try {
            client.admin().indices().prepareCreate(index)
                    .addMapping(type, XContentFactory.jsonBuilder()
                            .startObject()
                            .startObject(type)
                            .startObject("properties")
                            .startObject("name")
                            .field("type", "text")
                            .field("term_vector", "with_positions_offsets")
                            .field("store", "yes")
                            .endObject()
                            .startObject("tag")
                            .field("type", "keyword")
                            .endObject()
                            .endObject()
                            .endObject()
                            .endObject())
                    .execute().actionGet();
        } catch (IOException e) {
            System.out.println("Unable to create mapping");
        }
        client.admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setWaitForGreenStatus().execute().actionGet();

        BulkRequestBuilder bulker = client.prepareBulk();
        for (Integer i = 1; i <= 1000; i++) {
            bulker.add(client.prepareIndex(index, type, i.toString()).setSource("text", i.toString(), "number1", i + 1, "number2", i % 2, "tag", getTag()));
        }
        bulker.execute().actionGet();

        client.admin().indices().prepareRefresh(index).execute().actionGet();

    }

    public void dropIndex(String index) {
        io.deleteIndex(index);
    }

    public Client getClient() {
        return client;
    }
}
