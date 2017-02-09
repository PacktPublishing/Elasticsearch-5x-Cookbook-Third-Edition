curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '
{
    "size": 1,
    "query": {
        "match_all" : {}
    },
    "sort": [
        {"price": "asc"},
        {"_uid": "desc"}
    ]
}'

curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '
{
    "size": 1,
    "query": {
        "match_all" : {}
    },
    "search_after": [4.0, "test-type#1"],
    "sort": [
        {"price": "asc"},
        {"_uid": "desc"}
    ]
}'