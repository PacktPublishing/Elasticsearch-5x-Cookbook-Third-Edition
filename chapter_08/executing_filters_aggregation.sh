curl -XGET 'http://127.0.0.1:9200/test-index/test-type/_search?size=0&pretty' -d '
{
  "query": {
    "match_all": {}
  },
  "aggs": {
    "expensive_docs": {
      "filters": {
        "other_bucket": true,
        "other_bucket_key": "other_documents",
        "filters": {
          "2016_over_50": {
            "bool": {
              "must": [
                {
                  "range": {
                    "date": {
                      "gte": "2016-01-01"
                    }
                  }
                },
                {
                  "range": {
                    "price": {
                      "gte": 50
                    }
                  }
                }
              ]
            }
          },
          "previous_2016_over_50": {
            "bool": {
              "must": [
                {
                  "range": {
                    "date": {
                      "lt": "2016-01-01"
                    }
                  }
                },
                {
                  "range": {
                    "price": {
                      "gte": 50
                    }
                  }
                }
              ]
            }
          }
        }
      }
    }
  }
}'

