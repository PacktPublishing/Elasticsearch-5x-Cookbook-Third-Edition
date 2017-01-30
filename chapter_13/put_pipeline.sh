 curl -XPUT 'http://127.0.0.1:9200/_ingest/pipeline/add-user-john' -d '{
  "description" : "Add user john field",
  "processors" : [ 
     {
      "set" : {
        "field": "user",
        "value": "john"
      }
    }
  ],
  "version":1
}'
