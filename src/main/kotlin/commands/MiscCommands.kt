package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.jda.actor.SlashCommandActor
import revxrsal.commands.jda.annotation.CommandPermission
import java.util.*

object MiscCommands {
    val emojis = listOf("😀", "😁", "😂", "🤣", "😃", "😄", "😅", "😆", "😉", "😊", "😋", "😎", "😍", "😘", "🥰", "😗", "😙")

    @Command("hello")
    @Description("Say hello!")
    @CommandPermission(Permission.MESSAGE_SEND)
    fun hello(sender: SlashCommandActor) {
        sender.commandEvent().reply("Hello ${sender.commandEvent().member!!.effectiveName}! ${emojis.random()}").queue()
    }

    @Command("echo <message>")
    @Description("Echo a message")
    @CommandPermission(Permission.MESSAGE_SEND)
    fun echo(sender: SlashCommandActor, message: String) = sender.commandEvent().reply(message).queue()

    @Command("userinfo <member>")
    @CommandPermission(Permission.MESSAGE_SEND)
    fun userInfo(sender: SlashCommandActor, member: Member) {
        EmbedBuilder()
            .setTitle("User Info")
            .setDescription(
                """
                **Username**: ${member.effectiveName}
                **Nickname**: ${member.nickname}
                **ID**: ${member.id}
                **Joined Server**: <t:${Date.from(member.timeJoined.toInstant()).time / 1000}:F>
                **Roles**: ${member.roles.joinToString(", ") { it.name }}
                **Is Bot**: ${member.user.isBot}
            """.trimIndent()
            )
            .setColor(0x00FF00)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }
}