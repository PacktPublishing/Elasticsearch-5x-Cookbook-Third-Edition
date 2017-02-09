curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty' -d '{
    "query": {
            "has_child" : {
                "type" : "test-type2",
                "query" : {
                    "term" : {
                        "value" : "value1"
                    }
                },
                "inner_hits":{}
            }
        }
    }
}'

