package com.incubator4

import com.incubator4.command.FFXIVBot
import com.incubator4.config.CommandConfig
import com.incubator4.config.ProxyConfig
import com.incubator4.util.getProxyType
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.util.*
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.permission.AbstractPermitteeId
import net.mamoe.mirai.console.permission.PermissionService.Companion.permit
import net.mamoe.mirai.console.plugin.id
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.message.MessageSerializers
import net.mamoe.mirai.utils.info
import java.net.InetSocketAddress
import java.net.Proxy

object MiraiConsoleFFXIVBot : KotlinPlugin(
    JvmPluginDescription(
        id = "com.incubator4.mirai-console-ffxivbot",
        name = "mirai-console-ffxivbot",
        version = "1.0-SNAPSHOT",
    ) {
        author("incubator4")
        info("""mirai console native ffxivbot""")
    }
) {
    val cachePath = "/data/${id}/download"

    lateinit var client: HttpClient

    @OptIn(KtorExperimentalAPI::class)
    override fun onEnable() {
        logger.info { "Plugin loaded" }

        CommandConfig.reload()
        ProxyConfig.reload()

        client = HttpClient {
            engine {
                proxy = if (ProxyConfig.type != "DIRECT") Proxy(
                    getProxyType(ProxyConfig.type),
                    InetSocketAddress(ProxyConfig.hostname, ProxyConfig.port)
                ) else Proxy.NO_PROXY
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    serializersModule = MessageSerializers.serializersModule
                })
            }
        }

        FFXIVBot.register()

        try {
            AbstractPermitteeId.AnyContact.permit(FFXIVBot.permission)
        } catch (e: Exception) {
            logger.warning("无法自动授予权限，请自行使用权限管理来授予权限")
        }
    }

    override fun onDisable() {
        FFXIVBot.unregister()
        client.close()
        logger.info("Plugin unloaded")
    }

}