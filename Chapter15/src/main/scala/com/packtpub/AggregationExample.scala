package com.packtpub

import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.search.aggregations.metrics.geocentroid.InternalGeoCentroid
import org.elasticsearch.search.aggregations.metrics.stats.extended.InternalExtendedStats
import scala.collection.JavaConversions._

object AggregationExample extends App with ElasticSearchClientTrait {
  val indexName = "myindex"
  val typeName = "mytype"
  ensureIndexMapping(indexName, typeName)
  populateSampleData(indexName, typeName, 1000)

  val resp = client.execute {
    search(indexName / typeName) size 0 aggregations (
      termsAggregation("tag") field "tag" size 100 subAggregations(
        extendedStatsAggregation("price") field "price"
        , extendedStatsAggregation("size") field "size"
        ,geoBoundsAggregation("centroid") field "location"
      ))
  }.await

val tagsAgg = resp.aggregations.stringTermsResult("tag")

println(s"Result Hits: ${resp.size}")
println(s"number of tags: ${tagsAgg.getBuckets.size()}")
println(s"max price of first tag ${tagsAgg.getBuckets.head.getKey}: ${tagsAgg.getBuckets.head.getAggregations.get[InternalExtendedStats]("price").value("max")}")
println(s"min size of first tag ${tagsAgg.getBuckets.head.getKey}: ${tagsAgg.getBuckets.head.getAggregations.get[InternalExtendedStats]("size").value("min")}")
//println(s"center of first tag ${tagsAgg.getBuckets.head.getKey}: ${tagsAgg.getBuckets.head.getAggregations.get[InternalGeoCentroid]("centroid").centroid()}")

  client.execute(deleteIndex(indexName)).await

  client.close()
}
