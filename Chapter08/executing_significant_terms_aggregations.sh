curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true&size=0' -d '{
"query" : {
        "terms" : {"tag" : [ "ullam", "in", "ex" ]}
    },
        "aggs": {
        "significant_tags": {
            "significant_terms": {
                "field": "tag"
            }
        }
    }
}'

curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true&size=0' -d '{
"query" : {
        "terms" : {"tag" : [ "ullam", "in", "ex" ]}
    },
        "aggs": {
        "significant_tags": {
            "terms": {
                "field": "tag"
            }
        }
    }
}'

    