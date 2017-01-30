curl -XDELETE 'http://127.0.0.1:9200/myindex?pretty=true'
curl -XPUT 'http://127.0.0.1:9200/myindex?pretty=true' -d  '{"mappings": {"test-type": {"properties": {"pos": {"type": "integer", "store": "yes"}, "uuid": {"store": "yes", "type": "keyword"}, "parsedtext": {"term_vector": "with_positions_offsets",  "store": "yes", "type": "text"}, "name": {"term_vector": "with_positions_offsets",  "store": "yes", "type": "text"}, "title": {"term_vector": "with_positions_offsets",  "store": "yes", "type": "text"}}}}}'
curl -XPUT 'http://127.0.0.1:9200/myindex/test-type/1?pretty=true' -d '{"position": 1, "parsedtext": "Joe Testere nice guy", "name": "Joe Tester", "uuid": "11111", "price":4.0}'
curl -XPUT 'http://127.0.0.1:9200/myindex/test-type/3?pretty=true' -d '{"position": 3, "parsedtext": "Bill is not\n                nice guy", "name": "Bill Clinton", "uuid": "33333", "price":6.0}'
curl -XPOST 'http://127.0.0.1:9200/myindex/_refresh?pretty=true'
curl -XGET 'http://127.0.0.1:9200/_cluster/health?wait_for_status=green&timeout=0s&pretty=true'


