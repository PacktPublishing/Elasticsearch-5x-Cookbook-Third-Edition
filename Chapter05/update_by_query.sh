curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_update_by_query?pretty' -d '{"query":{"match_all":{}}}'

curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_update_by_query?pretty' -d '
{
"script": {
    "inline": "ctx._source.hit=4",
    "lang": "painless"
  },
"query":{"match_all":{}}
}
'

