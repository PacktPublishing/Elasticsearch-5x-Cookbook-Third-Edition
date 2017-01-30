curl -XGET 'http://127.0.0.1:9200/_ingest/pipeline/add-user-john?pretty'

curl -XGET 'http://127.0.0.1:9200/_ingest/pipeline/add-user-john?filter_path=*.version&pretty'
