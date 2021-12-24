package com.incubator4.types.item

import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val Page: Int,
    val PageNext: Int?,
    val PagePrev: Int?,
    val PageTotal: Int,
    val Results: Int,
    val ResultsPerPage: Int,
    val ResultsTotal: Int
)
