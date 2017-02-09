package org.elasticsearch.plugin.simple;

import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.simple.SimpleAction;
import org.elasticsearch.action.simple.TransportSimpleAction;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.RestSimpleAction;

import java.util.List;

import static java.util.Collections.singletonList;

public class RestPlugin extends Plugin implements ActionPlugin {

    @Override
    public List<Class<? extends RestHandler>> getRestHandlers() {
        return singletonList(RestSimpleAction.class);
    }

    @Override
    public List<ActionHandler<? extends ActionRequest<?>, ? extends ActionResponse>> getActions() {
        return singletonList(new ActionHandler<>(SimpleAction.INSTANCE, TransportSimpleAction.class));
    }

}
