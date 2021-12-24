package com.incubator4.types

data class DataCenterCurrentlyShownData(
    val itemID: Long,
    val lastUploadTime: Long,
    val listings: List<ItemRecord>,
    val recentHistory: List<RecentHistory>,
    val dcName: String,
    val currentAveragePrice: Double,
    val currentAveragePriceNQ: Double,
    val currentAveragePriceHQ: Double,
    val regularSaleVelocity: Double,
    val nqSaleVelocity: Double,
    val hqSaleVelocity: Double,
    val averagePrice: Double,
    val averagePriceNQ: Double,
    val averagePriceHQ: Double,
    val minPrice: Long,
    val minPriceNQ: Long,
    val minPriceHQ: Long,
    val maxPrice: Long,
    val maxPriceNQ: Long,
    val maxPriceHQ: Long,
    val stackSizeHistogram: Map<String, Long>,
    val stackSizeHistogramNQ: Map<String, Long>,
    val stackSizeHistogramHQ: Map<String, Long>,
    val worldUploadTimes: Map<String, Long>
)
