package com.packtpub;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

import java.net.UnknownHostException;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class ScrollQueryExample {
    public static void main(String[] args) throws UnknownHostException {
        String index = "mytest";
        String type = "mytype";
        QueryHelper qh = new QueryHelper();
        qh.populateData(index, type);
        Client client = qh.getClient();

        QueryBuilder query = boolQuery().must(rangeQuery("number1").gte(500)).filter(termQuery("number2", 1));

        SearchResponse response = client.prepareSearch(index).setTypes(type).setSize(30)
                .setQuery(query).setScroll(TimeValue.timeValueMinutes(2))
                .execute().actionGet();

        do {
            for (SearchHit hit : response.getHits().getHits()) {
                System.out.println("hit: " + hit.getIndex() + ":" + hit.getType() + ":" + hit.getId());
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(TimeValue.timeValueMinutes(2)).execute().actionGet();
        } while (response.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.


        qh.dropIndex(index);

        //we need to close the client to free resources
        client.close();

    }
}
