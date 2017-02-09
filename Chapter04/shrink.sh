curl -XGET ''http://localhost:9200/_nodes?pretty'

curl -XGET 'http://localhost:9200/_cluster/health?pretty'

curl -XPUT 'http://localhost:9200/myindex/_settings' -d '
{
  "settings": {
    "index.routing.allocation.require._name": "Gin Genie", 
    "index.blocks.write": true 
  }
}'


curl -XPOST 'http://localhost:9200/myindex/_shrink/reduced_index' -d '
{
  "settings": {
    "index.number_of_replicas": 1,
    "index.number_of_shards": 1, 
    "index.codec": "best_compression" 
  },
  "aliases": {
    "my_search_indices": {}
  }
}'

curl -XGET 'http://localhost:9200/_cluster/health?wait_for_status=yellow'

