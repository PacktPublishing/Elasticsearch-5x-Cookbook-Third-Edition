curl -XPOST "http://127.0.0.1:9200/myindex/test-type/_search?size=0&pretty" -d '{
  "aggs": {
    "uuid": {
      "terms": {
        "field": "uuid",
        "size": 10
      },
      "aggs": {
        "to-children": {
          "children": {
            "type": "test-type2"
          },
          "aggs": {
            "top-values": {
              "terms": {
                "field": "value.keyword",
                "size": 10
              }
            }
          }
        }
      }
    }
  }
}'

