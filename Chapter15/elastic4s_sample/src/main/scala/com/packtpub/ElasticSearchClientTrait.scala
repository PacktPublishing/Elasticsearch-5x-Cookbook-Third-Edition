package com.packtpub

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.{ElasticClient, ElasticsearchClientUri}
import com.sksamuel.elastic4s.indexes._
import org.elasticsearch.cluster.health.ClusterHealthStatus

trait ElasticSearchClientTrait {
  val client:ElasticClient ={
    val uri = ElasticsearchClientUri("elasticsearch://127.0.0.1:9300?cluster.name=elasticsearch")
    ElasticClient.transport(uri)
  }

  def ensureIndexMapping(indexName:String, mappingName:String): Unit ={
    if(client.execute{ indexExists(indexName)}.await.isExists){
      client.execute{ deleteIndex(indexName)}.await
    }

    client.execute{
      createIndex(indexName) shards 1 replicas 0 mappings (
        mapping(mappingName).as (
          textField("name") termVector "with_positions_offsets" stored true,
          longField("size"),
          doubleField("price"),
          geopointField("location"),
          keywordField("tag") stored true
        )
        )
    }.await

    client.execute{
      clusterHealth  waitForStatus ClusterHealthStatus.YELLOW
    }

  }

  def populateSampleData(indexName:String, mappingName:String, size:Int=1000): Unit ={
    import scala.util.Random
    val tags=List("cool", "nice", "bad", "awesome", "good")
    client.execute{
      bulk(0.to(size).map{
        i=>
          indexInto(indexName, mappingName) id i fields(
              "name" -> s"name_${i}",
              "size" -> (i%10)*8,
              "price" -> (i%10) * 1.2,
              "location" -> List(30.0 * Random.nextDouble(), 30.0* Random.nextDouble()),
              "tag" -> Random.shuffle(tags).take(3)
            )
      })
    }.await

    Thread.sleep(2000)

  }
}
