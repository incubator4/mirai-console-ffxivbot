package com.incubator4

import com.incubator4.types.item.SearchData
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.*
import kotlinx.serialization.*
import java.net.Proxy

@OptIn(KtorExperimentalAPI::class)
fun main() {
    runBlocking {
        val client = HttpClient(CIO) {
            engine {
                proxy = Proxy.NO_PROXY
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        val data: SearchData = client.get(
            host = "cafemaker.wakingsands.com",
            scheme = "https",
            path = "/search?indexes=Item&string=棉布",)
        println("${data.Pagination.Page}/${data.Pagination.PageTotal}")
        println("${data.Pagination.Results}/${data.Pagination.ResultsTotal}")
        data.Results.forEach{ result -> println("${result.Name} ${result.ID}") }
    }
}