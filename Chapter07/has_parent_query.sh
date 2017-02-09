curl -XPOST 'http://127.0.0.1:9200/test-index/test-type2/_search?pretty=true' -d '{
    "query": {
            "has_parent" : {
                "type" : "test-type",
                "query" : {
                    "term" : {
                        "parsedtext" : "joe"
                    }
                }
            }
        }
    }
}'
