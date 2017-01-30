package com.packtpub;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.net.UnknownHostException;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class QueryExample {
    public static void main(String[] args) throws UnknownHostException {
        String index = "mytest";
        String type = "mytype";
        QueryHelper qh = new QueryHelper();
        qh.populateData(index, type);
        Client client = qh.getClient();

        QueryBuilder query = boolQuery().must(rangeQuery("number1").gte(500)).filter(termQuery("number2", 1));

        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(query).highlighter(new HighlightBuilder().field("name"))
                .execute().actionGet();
        if (response.status().getStatus() == 200) {
            System.out.println("Matched number of documents: " + response.getHits().totalHits());
            System.out.println("Maximum score: " + response.getHits().maxScore());

            for (SearchHit hit : response.getHits().getHits()) {
                System.out.println("hit: " + hit.getIndex() + ":" + hit.getType() + ":" + hit.getId());
            }
        }
        qh.dropIndex(index);

        //we need to close the client to free resources
        client.close();

    }
}
