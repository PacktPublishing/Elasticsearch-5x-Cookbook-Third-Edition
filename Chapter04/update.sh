curl -XPOST 'http://localhost:9200/myindex/order/2qLrAfPVQvCRMe7Ku8r0Tw/_update?pretty' -d '{
  "script" : {
    "inline":"ctx._source.in_stock_items += params.count",
    "params" : {
      "count" : 4
    }
  }
}'
