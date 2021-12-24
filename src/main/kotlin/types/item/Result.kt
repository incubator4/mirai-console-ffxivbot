package com.incubator4.types.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val ID: Long,
    val Icon: String,
    val Name: String,
    val Url: String,
    val UrlType: String,
    @SerialName("_")
    val foo: String,
    val _Score: String

)
