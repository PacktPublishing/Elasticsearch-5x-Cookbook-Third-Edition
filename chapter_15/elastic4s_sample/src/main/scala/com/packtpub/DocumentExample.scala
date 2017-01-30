package com.packtpub

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.circe._

object DocumentExample extends App with ElasticSearchClientTrait{
  val indexName="myindex"
  val typeName="mytype"

  ensureIndexMapping(indexName, typeName)

  client.execute {
    indexInto(indexName / typeName) id "0" fields (
      "name" -> "brown",
      "tag" -> List("nice", "simple")
    )
  }.await

  val bwn=client.execute {
    get("0") from indexName / typeName
  }.await

  println(bwn.sourceAsString)

  client.execute {
    update("0").in(indexName / typeName).script("ctx._source.name = 'red'")
  }.await

  val red=client.execute {
    get("0") from indexName / typeName
  }.await

  println(red.sourceAsString)

  client.execute {
    delete("0") from indexName / typeName
  }.await

  case class Place(id: Int, name: String)
  case class Cafe(name: String, place: Place)

  import io.circe.generic.auto._
  import com.sksamuel.elastic4s.Indexable
  implicitly[Indexable[Cafe]]

  val cafe=Cafe("nespresso", Place(20,"Milan"))

  client.execute {
    indexInto(indexName / typeName).id(cafe.name).source(cafe)
  }.await



  client.execute(deleteIndex(indexName)).await

  client.close()
}
