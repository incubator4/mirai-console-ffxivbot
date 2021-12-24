package com.incubator4.types

import kotlinx.serialization.Serializable

@Serializable
data class RecentHistory (
    val hq: Boolean,
    val pricePerUnit: Long,
    val quantity: Long,
    val timestamp: Long,
    val buyerName: String,
    val total: Long
)
