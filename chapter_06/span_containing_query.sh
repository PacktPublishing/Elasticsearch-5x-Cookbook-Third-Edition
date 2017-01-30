curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
    "query": {
        "span_containing" : {
            "little" : {
                "span_term" : { "parsedtext" : "nice" }
            },
            "big" : {
                "span_near" : {
                    "clauses" : [
                        { "span_term" : { "parsedtext" : "not" } },
                        { "span_term" : { "parsedtext" : "guy" } }
                    ],
                    "slop" : 5,
                    "in_order" : true
                }
            }
        }
    }
}'
