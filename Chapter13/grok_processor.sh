 curl -XPOST 'http://127.0.0.1:9200/_ingest/pipeline/_simulate?pretty' -d '{
  "pipeline": {
    "description": "Testing grok pattern",
    "processors": [
      {
        "grok": {
          "field": "message",
          "patterns": [
            "%{IP:client} %{WORD:method} %{URIPATHPARAM:request} %{NUMBER:bytes} %{NUMBER:duration}"
          ]
        }
      }
    ]
  },
  "docs": [
    {
      "_index": "index",
      "_type": "type",
      "_id": "1",
      "_source": {
        "message": "155.2.124.3 GET /index.html 15442 0.038"
      }
    }
  ]
}'

curl -XPOST 'http://127.0.0.1:9200/_ingest/pipeline/_simulate?pretty' -d '{
  "pipeline": {
  "description" : "custom grok pattern",
  "processors": [
    {
      "grok": {
        "field": "message",
        "patterns": ["my favorite color is %{COLOR:color}"],
        "pattern_definitions" : {
          "COLOR" : "RED|GREEN|BLUE"
        }
      }
    }
  ]
},
"docs":[
  {
    "_source": {
      "message": "my favorite color is RED"
    }
  },
  {
    "_source": {
      "message": "happy fail!!"
    }
  }
  ]
}'