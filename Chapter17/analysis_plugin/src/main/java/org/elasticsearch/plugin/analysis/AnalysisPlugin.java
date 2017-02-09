package org.elasticsearch.plugin.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.CustomEnglishAnalyzerProvider;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class AnalysisPlugin extends Plugin implements org.elasticsearch.plugins.AnalysisPlugin {
    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> analyzers = new HashMap();
        analyzers.put(CustomEnglishAnalyzerProvider.NAME, CustomEnglishAnalyzerProvider::getCustomEnglishAnalyzerProvider);
        return analyzers;
    }

}
