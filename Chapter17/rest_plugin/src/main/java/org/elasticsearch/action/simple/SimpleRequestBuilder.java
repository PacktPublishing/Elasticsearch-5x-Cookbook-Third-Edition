package org.elasticsearch.action.simple;

import org.elasticsearch.action.support.broadcast.BroadcastOperationRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * A request to get simples of one or more indices.
 */
public class SimpleRequestBuilder extends BroadcastOperationRequestBuilder<SimpleRequest, SimpleResponse, SimpleRequestBuilder> {

    public SimpleRequestBuilder(ElasticsearchClient client, SimpleAction action) {
        super(client, action, new SimpleRequest());
    }

}
