package com.incubator4.types

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDateTime
import kotlin.collections.ArrayList

@Serializable
data class ItemRecord(
    val lastReviewTime: LocalDateTime,
    val pricePerUnit: Int,
    val quantity: Int,
    val stainsID: Int,
    val creatorName: String,
    val creatorID: String,
    val hq: Boolean,
    val isCrafted: Boolean,
    val listingID: String,
    val materia: ArrayList<String>,
    val onMannequin: Boolean,
    val retainerCity: Int,
    val retainerID: String,
    val retainerName: String,
    val sellerID: String,
    val total: Int
)