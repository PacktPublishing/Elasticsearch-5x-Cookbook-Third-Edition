package com.packtpub

import com.sksamuel.elastic4s.ElasticDsl._

object MappingExample extends App with ElasticSearchClientTrait{
  val indexName="myindex"
  if(client.execute{ indexExists(indexName)}.await.isExists){
    client.execute{ deleteIndex(indexName)}.await
  }

  client.execute{
    createIndex(indexName) shards 1 replicas 0 mappings (
      mapping("mytype")as (
        textField("name").termVector("with_positions_offsets").stored(true)
      )
      )
  }.await
  Thread.sleep(2000)

  client.execute{
    putMapping(indexName / "mytype").as(
      keywordField("tag")
    )
  }.await

  val myMapping=client.execute{
    getMapping(indexName / "mytype")
  }.await

  val tagMapping=myMapping.fieldFor(indexName / "mytype", "tag")
  println(tagMapping)

  client.execute(deleteIndex(indexName)).await

  client.close()
}
