package com.incubator4.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object ProxyConfig : AutoSavePluginConfig("ProxyConfig") {

    /**
     * 代理类型
     */
    @ValueDescription("可选：DIRECT/HTTP/SOCKS")
    val type: String by value("DIRECT")

    /**
     * IP地址
     */
    @ValueDescription("IP地址")
    val hostname: String by value("localhost")

    /**
     * 端口
     */
    @ValueDescription("端口")
    val port: Int by value(1080)
}