curl -XPOST 'http://127.0.0.1:9200/test-index/test-type/9/_update?&pretty=true' -d '
{
  "script": {
    "inline": "ctx.op = \"none\";\n  if(ctx._source.containsValue(\"tags\")){\n    for(def item : params.new_tags){\n      if(!ctx._source.tags.contains(item)){\n        ctx._source.tags.add(item);\n        ctx.op = \"index\";\n      }\n    }\n  }else{ ctx._source.tags=params.new_tags; ctx.op = \"index\"  }\n  if(ctx._source.containsValue(\"labels\")){\n    for(def item : params.new_labels){\n      if(!ctx._source.labels.contains(item)){\n        ctx._source.labels.add(item);\n        ctx.op = \"index\"\n      }\n    }\n  }else{\n    ctx._source.labels=params.new_labels;\n    ctx.op = \"index\"}",
    "params": {
      "new_tags": [
        "cool",
        "nice"
      ],
      "new_labels": [
        "red",
        "blue",
        "green"
      ]
    }
  }
}'
