curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
    "query": {
        "span_not" : {
            "include" : {
                "span_term" : { "parsedtext" : "nice" }
            },
            "exclude" : {
                "span_near" : {
                    "clauses" : [
                        { "span_term" : { "parsedtext" : "not" } },
                        { "span_term" : { "parsedtext" : "nice" } }
                    ],
                    "slop" : 0,
                    "in_order" : true
                }
            }
        }
    }
}'
