curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
    "query": {
        "nested" : {
            "path" : "versions",
            "score_mode" : "avg",
            "query" : {
                "bool" : {
                    "must" : [
                    { "term" : {"versions.color" : "blue"} },
                    { "range" : {"versions.size" : {"gt" : 10}} }
                    ]
                }
            }
        }
    }
}'

