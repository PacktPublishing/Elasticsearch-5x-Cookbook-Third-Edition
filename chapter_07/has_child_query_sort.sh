curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/_search?pretty=true' -d '{
   "query": {
            "has_child" : {
                "type" : "test-type2",
                "score_mode" : "max",
                "query" : {
                    "function_score" : {
                        "script_score": {
                            "script": "doc[\"amount\"].value"
                           }
                    }
                }
            }
        }
    }
}'
