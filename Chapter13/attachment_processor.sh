curl -XPUT 'http://127.0.0.1:9200/_ingest/pipeline/attachment' -d '{
  "description" : "Extract data from an attachment via Tika",
  "processors" : [ 
     {
      "attachment" : {
        "field" : "data"
      }
    }
  ],
  "version":1
}'

curl -XPUT 'http://127.0.0.1:9200/my_index/my_type/my_id?pipeline=attachment' -d '{
  "data": "e1xydGYxXGFuc2kNCkxvcmVtIGlwc3VtIGRvbG9yIHNpdCBhbWV0DQpccGFyIH0="
}'

curl -XGET 'http://127.0.0.1:9200/my_index/my_type/my_id?pretty'
