package dev.shreyasayyengar.util

import dev.shreyasayyengar.util.LocalisedMessage.Plain
import net.dv8tion.jda.api.entities.MessageEmbed

object LocaleManager {
    val jsonData: String = javaClass.getResource("/messages.json").readText()
//    val welcomeMessage: LocalisedMessage = deserialise()


    fun deserialise(jsonData: String) : LocalisedMessage {
        // if jsonData is a plain string, return Plain(jsonData)
        // if jsonData is an embed, return Embed(jsonData)
        if (jsonData.isNotEmpty()) {
            if (jsonData.startsWith("{")) {

            } else return Plain(jsonData.split(":")[1].trim())
        }

        return TODO()
    }
}

sealed class LocalisedMessage {
    data class Plain(val message: String) : LocalisedMessage()
    data class Embed(val embed: MessageEmbed) : LocalisedMessage()
}