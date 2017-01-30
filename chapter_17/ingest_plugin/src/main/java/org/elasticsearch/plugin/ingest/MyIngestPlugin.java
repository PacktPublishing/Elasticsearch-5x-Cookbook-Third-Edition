package org.elasticsearch.plugin.ingest;


import com.packtpub.ingest.InitialProcessor;
import org.elasticsearch.ingest.Processor;
import org.elasticsearch.plugins.IngestPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.Map;

public class MyIngestPlugin extends Plugin implements IngestPlugin {
    @Override
    public Map<String, Processor.Factory> getProcessors(Processor.Parameters parameters) {
        return Collections.singletonMap(InitialProcessor.TYPE,
                (factories, tag, config) -> new InitialProcessor.Factory().create(factories, tag, config));
    }
}
