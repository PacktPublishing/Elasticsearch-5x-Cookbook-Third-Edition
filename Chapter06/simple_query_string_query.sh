curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
  "query": {
    "simple_query_string": {
      "query": "\"nice guy\" -not",
      "fields": [
        "parsedtext^5",
        "_all"
      ],
      "default_operator": "and"
    }
  }
}'