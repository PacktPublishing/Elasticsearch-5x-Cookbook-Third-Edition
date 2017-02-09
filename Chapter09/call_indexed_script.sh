 curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?&pretty=true&size=3' -d '{
  "query": {
    "match_all": {}
  },
  "sort": {
    "_script": {
      "script":{
        "id": "my_script",
        "lang": "groovy",
        "params": {
          "factor": 1.1
        }
      },
      "type": "number",
      "order": "asc"
    }
  }
}'
