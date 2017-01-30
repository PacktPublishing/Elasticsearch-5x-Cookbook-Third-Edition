package org.elasticsearch.action.simple;

import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

public class SimpleAction extends Action<SimpleRequest, SimpleResponse, SimpleRequestBuilder> {

    public static final SimpleAction INSTANCE = new SimpleAction();
    public static final String NAME = "custom:indices/simple";

    private SimpleAction() {
        super(NAME);
    }

    @Override
    public SimpleResponse newResponse() {
        return new SimpleResponse();
    }

    @Override
    public SimpleRequestBuilder newRequestBuilder(ElasticsearchClient elasticsearchClient) {
        return new SimpleRequestBuilder(elasticsearchClient, this);
    }

}
