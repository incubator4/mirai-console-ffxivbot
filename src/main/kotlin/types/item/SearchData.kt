package com.incubator4.types.item

import kotlinx.serialization.Serializable

@Serializable
data class SearchData(
    val Pagination: Pagination,
    val Results: List<Result>,
    val SpeedMs: Int
)
