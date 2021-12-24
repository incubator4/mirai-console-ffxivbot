package com.incubator4.command

import com.incubator4.MiraiConsoleFFXIVBot
import com.incubator4.config.CommandConfig
import com.incubator4.util.*
import com.incubator4.util.logger
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.descriptor.ExperimentalCommandDescriptors
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.contact.Contact.Companion.uploadImage
import net.mamoe.mirai.message.data.*
import java.lang.Exception

object FFXIVBot : CompositeCommand(
    MiraiConsoleFFXIVBot,
    primaryName = "ffxiv",
    secondaryNames = CommandConfig.ffxivbot
) {
    private val help: String

    @ExperimentalCommandDescriptors
    @ConsoleExperimentalApi
    override val prefixOptional: Boolean = true

    init {
        help = "this is demo help"
//        val helpFile = "help.txt"
//        help = javaClass.classLoader.getResource(helpFile)?.readText() ?: throw Exception("Not Find ${helpFile}")
    }

    @SubCommand("item", "物品")
    @Description("查询物品信息")
    suspend fun CommandSender.item(query: String) {
        if (!lock(subject)) {
            logger.info("throttled")
            return
        }
        try {
            val response = searchItems(query)
            val (pagination, results) = response
            val mc = buildMessageChain {
                +PlainText("共计${pagination.ResultsTotal} 展示${pagination.Results} " +
                        "${pagination.Page}/${pagination.PageTotal}页\n")
                results.map { result ->
                    val imageMessage: Message = try {
                        val imageStream = downloadImage("https://cafemaker.wakingsands.com${result.Icon}")
                        subject?.uploadImage(imageStream) ?: PlainText("图片上传失败")
                    }catch (e: Exception) {
                        PlainText(e.toString())
                    }

                    +imageMessage
                    +PlainText("name: ${result.Name} id: ${result.ID}\n")
                }
            }
            sendMessage(mc)

        } catch (e: Exception) {
            sendMessage(e.toString())
        }finally {
            unlock(subject)
        }
    }

    @SubCommand("板子", "market", "board", "marketboard")
    @Description("查询市场布告板物品价格")
    suspend fun CommandSender.market(region: String, item: String) {
        if (!lock(subject)) {
            logger.info("throttled")
            return
        }
        val regionResponse = getGroupedDataCenter()
        if (regionResponse.isEmpty()) {
            unlock(subject)
            return
        }
        val itemResponse = when (region) {
            in regionResponse.keys ->
                geItemDataFromDataCenter(region, itemID = 0)
            in regionResponse.map { (_, v) -> v }.map { (_, v) -> v } ->
                geItemDataFromDataCenter(region, 0)
            else -> null
        }
    }
}