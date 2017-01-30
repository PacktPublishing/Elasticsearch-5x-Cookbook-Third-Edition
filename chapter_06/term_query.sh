curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
    "query": {
        "term": {
            "uuid": "33333"
        }
    }
}'


