curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?pretty' -d '{
    "profile": true,
    "query": {
        "term": {
            "uuid": "11111"
        }
    }
}'

