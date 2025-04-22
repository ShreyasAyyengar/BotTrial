package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import revxrsal.commands.annotation.Command
import revxrsal.commands.jda.actor.SlashCommandActor

object MiscCommands {
    @Command("echo <message>")
    suspend fun echo(sender: SlashCommandActor, message: String) = sender.reply(message)

    val emojis = listOf("😀", "😁", "😂", "🤣", "😃", "😄", "😅", "😆", "😉", "😊", "😋", "😎", "😍", "😘", "🥰", "😗", "😙")

    @Command("hello")
    suspend fun hello(sender: SlashCommandActor) {
        sender.reply("Hello ${sender.commandEvent().member!!.effectiveName}! ${emojis.random()}")
    }

    @Command("userinfo <member>")
    suspend fun userInfo(sender: SlashCommandActor, member: Member) {
        EmbedBuilder()
    }
}