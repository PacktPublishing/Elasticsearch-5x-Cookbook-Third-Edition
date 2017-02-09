curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
    "query": {
        "span_within" : {
            "little" : {
                "span_term" : { "parsedtext" : "nice" }
            },
            "big" : {
                "span_near" : {
                    "clauses" : [
                        { "span_term" : { "parsedtext" : "is" } },
                        { "span_term" : { "parsedtext" : "not" } }
],
                    "slop" : 1,
                    "in_order" : true
                }
            }
        }
    }
}'

