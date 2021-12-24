package com.incubator4.util

import com.incubator4.MiraiConsoleFFXIVBot
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.io.ByteArrayInputStream
import java.io.InputStream

/**
 * 从 [url] 下载图片, 并返回 [ByteArrayInputStream] 形式的图片数据
 *
 * @param url 来自 [ImageData.urls] 的 URL
 * @return 图片字节输入流
 * @see ImageData
 */
suspend fun downloadImage(url: String): InputStream {
    val response: HttpResponse = MiraiConsoleFFXIVBot.client.get(url)
    val result: ByteArray = response.receive()
    return ByteArrayInputStream(result)
}