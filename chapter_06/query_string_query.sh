curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
  "query": {
    "query_string": {
      "query": "\"nice guy\" -parsedtext:not price:{ * TO 5 } ",
      "fields": [
        "parsedtext^5"
      ],
      "default_operator": "and"
    }
  }
}'