 curl -XPOST 'http://127.0.0.1:9200/_ingest/pipeline/_simulate?pretty' -d '{
  "pipeline": {
    "description": "Testing some build-processors",
    "processors": [
      {
        "dot_expander": {
          "field": "extfield.innerfield"
        }
      },
      {
        "remove": {
          "field": "unwanted"
        }
      },
      {
        "trim": {
          "field": "message"
        }
      },
      {
        "set": {
          "field": "tokens",
          "value": "{{message}}"
        }
      },
      {
        "split": {
          "field": "tokens",
          "separator": "\\s+"
        }
      },
      {
        "sort": {
          "field": "tokens",
          "order": "desc"
        }
      },
      {
        "convert": {
          "field": "mynumbertext",
          "target_field": "mynumber",
          "type": "integer"
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
        "extfield.innerfield": "booo",
        "unwanted": 32243,
        "message": "   155.2.124.3 GET /index.html 15442 0.038   ",
        "mynumbertext": "3123"
      }
    }
  ]
}'
