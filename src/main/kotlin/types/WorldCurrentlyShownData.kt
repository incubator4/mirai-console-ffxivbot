package com.incubator4.types

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class WorldCurrentlyShownData(
    val itemID: Int,
    val worldID: Int,
    val lastUploadTime: LocalDateTime,
    val listings: List<ItemRecord>,
    val recentHistory: List<RecentHistory>,
    val currentAveragePrice: Double,
    val currentAveragePriceNQ: Double,
    val currentAveragePriceHQ: Long,
    val regularSaleVelocity: Double,
    val nqSaleVelocity: Double,
    val hqSaleVelocity: Double,
    val averagePrice: Double,
    val averagePriceNQ: Double,
    val averagePriceHQ: Long,
    val minPrice: Long,
    val minPriceNQ: Long,
    val minPriceHQ: Long,
    val maxPrice: Long,
    val maxPriceNQ: Long,
    val maxPriceHQ: Long,
    val stackSizeHistogram: Map<String, Long>,
    val stackSizeHistogramNQ: Map<String, Long>,
    val stackSizeHistogramHQ: Map<String, Long>,
    val worldName: String
)