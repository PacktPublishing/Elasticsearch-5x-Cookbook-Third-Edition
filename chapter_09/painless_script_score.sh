curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?&pretty&size=2' -d @painless_script_score.json
