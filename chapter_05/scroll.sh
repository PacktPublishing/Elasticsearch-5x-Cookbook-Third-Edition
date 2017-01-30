curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true&scroll=10m&size=1' -d '  {
  "query": {
    "match_all": {}
  },
  "sort": [
    "_doc"
  ]
}'


curl -XDELETE localhost:9200/_search/scroll -d '
{
    "scroll_id" : ["DnF1ZXJ5VGhlbkZldGNoBQAA..."]
}'