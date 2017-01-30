curl -XGET http://127.0.0.1:9200/test-mindex/_search -d '{
    "query": {
                "geo_distance": {
                    "pin.location": {
                        "lat": 40,
                        "lon": 70
                    },
                    "distance": "200km",
                    "optimize_bbox": "memory"
                }
            }
    }
}'
