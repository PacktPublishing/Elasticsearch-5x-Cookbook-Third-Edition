curl -XPUT 'http://127.0.0.1:9200/_ingest/pipeline/geoip' -d '{
  "description" : "Extract geopoint from an IP",
  "processors" : [ 
     {
      "geoip" : {
        "field" : "ip"
      }
    }
  ],
  "version":1
}'

curl -XPUT 'http://127.0.0.1:9200/my_index/my_type/my_id?pipeline=geoip' -d '{
  "ip": "8.8.8.8"
}'

curl -XGET 'http://127.0.0.1:9200/my_index/my_type/my_id?pretty'

