curl -XGET http://127.0.0.1:9200/test-mindex/_search -d '{
    "query": {
                "geo_polygon": {
                    "pin.location": {
                        "points": [
                            {
                                "lat": 50,
                                "lon": -30
                            },
                            {
                                "lat": 30,
                                "lon": -80
                            },
                            {
                                "lat": 80,
                                "lon": -90
                            }
                        ]
                    }
                }
            }
}'
