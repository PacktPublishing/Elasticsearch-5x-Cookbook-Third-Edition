 curl -XPOST 'http://127.0.0.1:9200/_ingest/pipeline/_simulate?pretty' -d '{
  "pipeline": {
    "description": "Add user john field",
    "processors": [
      {
        "set": {
          "field": "user",
          "value": "john"
        }
      },
      {
        "set": {
          "field": "job",
          "value": 10
        }
      }
    ],
    "version": 1
  },
  "docs": [
    {
      "_index": "index",
      "_type": "type",
      "_id": "1",
      "_source": {
        "name": "docs1"
      }
    },
    {
      "_index": "index",
      "_type": "type",
      "_id": "2",
      "_source": {
        "name": "docs2"
      }
    }
  ]
}'

 curl -XPOST 'http://127.0.0.1:9200/_ingest/pipeline/_simulate?verbose&pretty' -d '{
  "pipeline": {
    "description": "Add user john field",
    "processors": [
      {
        "set": {
          "field": "user",
          "value": "john"
        }
      },
      {
        "set": {
          "field": "job",
          "value": 10
        }
      }
    ],
    "version": 1
  },
  "docs": [
    {
      "_index": "index",
      "_type": "type",
      "_id": "1",
      "_source": {
        "name": "docs1"
      }
    },
    {
      "_index": "index",
      "_type": "type",
      "_id": "2",
      "_source": {
        "name": "docs2"
      }
    }
  ]
}'
