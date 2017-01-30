curl -XPOST "http://127.0.0.1:9200/_reindex?pretty" -d '{
  "source": {
    "index": "myindex"
  },
  "dest": {
    "index": "myindex2"
  }
}'

