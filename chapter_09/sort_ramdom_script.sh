curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?&pretty&size=3' -d '{
  "query": {
    "match_all": {}
  },
  "sort": {
    "_script": {
      "script": {
        "inline": "Math.random()"
      },
      "type": "number",
      "order": "asc"
    }
  }
}'
