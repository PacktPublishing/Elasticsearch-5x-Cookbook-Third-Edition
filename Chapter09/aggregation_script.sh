curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true&size=0' -d ' {
  "aggs": {
    "my_value": {
      "sum": {
        "script": {
          "inline": "doc[\"price\"].value * doc[\"price\"].value"
        }
      }
    }
  }
}'

 curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true&size=0' -d '{
  "aggs": {
    "my_value": {
      "sum": {
        "field":"price",
        "script": {
          "inline": "_value * _value"
        }
      }
    }
  }
}'

curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true&size=0' -d '{
  "aggs": {
    "my_value": {
      "terms": {
        "field":"tag",
"size":5,
          "script": {
              "inline": "if(params.replace.containsKey(_value.toUpperCase())) { params.replace[_value.toUpperCase()] } else { _value.toUpperCase() }",
              "params":{"replace":{"PORRO":"Result1", "LABORUM":"Result2"}}
        }
      }
    }
  }
}'
