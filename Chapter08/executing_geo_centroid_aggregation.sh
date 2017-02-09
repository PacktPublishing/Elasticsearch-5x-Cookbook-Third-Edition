curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?pretty&size=0' -d ' {
    "query" : {
        "match_all" : {}
    },
    "aggs" : {
        "centroid" : {
            "geo_centroid" : {
                "field":"position"
            }
        }
    }
}'