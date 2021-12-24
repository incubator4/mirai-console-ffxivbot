package com.incubator4.util

import com.incubator4.MiraiConsoleFFXIVBot
import com.incubator4.types.DataCenterCurrentlyShownData
import com.incubator4.types.WorldCurrentlyShownData
import com.incubator4.types.item.SearchData
import io.ktor.client.request.*

suspend fun getGroupedDataCenter(): Map<String, Array<String>> {
    return MiraiConsoleFFXIVBot.client.get(
        scheme = "https",
        host = "cafemaker.wakingsands.com",
        path = "/servers/dc"
    )
}

suspend fun getItemPriceFromWorld(world: String, itemID: Int): WorldCurrentlyShownData {
    return MiraiConsoleFFXIVBot.client.get(
        host = "universalis.app",
        scheme = "https",
        path = "/api/${world}/${itemID}"
    )
}

suspend fun geItemDataFromDataCenter(dataCenter: String, itemID: Int) : DataCenterCurrentlyShownData{
    return MiraiConsoleFFXIVBot.client.get(
        host = "universalis.app",
        scheme = "https",
        path = "/api/${dataCenter}/${itemID}"
    )
}

suspend fun searchItems(query: String): SearchData {
    val path =  if ("=" in query) "/search?${query}"
    else "/search?indexes=Item&string=${query}"
    return MiraiConsoleFFXIVBot.client.get(
        host = "cafemaker.wakingsands.com",
        scheme = "https",
        path = path,
    )
}