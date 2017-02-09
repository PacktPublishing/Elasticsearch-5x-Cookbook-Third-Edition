curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
    "query": {
        "multi_match" : {
            "fields":["parsedtext", "name"]
                "query": "nice guy",
                "operator": "and"
        }
    }
}'
