curl  -XPUT 'http://127.0.0.1:9200/mylogs-000001' -d '
{
  "aliases": {
    "logs_write": {}
  }
}'

curl  -XPOST 'http://127.0.0.1:9200/logs_write/_rollover' -d '
{
  "conditions": {
    "max_age":   "7d",
    "max_docs":  100000
  },
  "settings": {
    "index.number_of_shards": 3
  }
}'
