package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import revxrsal.commands.annotation.Command
import revxrsal.commands.jda.actor.SlashCommandActor

object MiscCommands {
    @Command("echo <message>")
    fun echo(sender: SlashCommandActor, message: String) = sender.reply(message)

    val emojis = listOf("😀", "😁", "😂", "🤣", "😃", "😄", "😅", "😆", "😉", "😊", "😋", "😎", "😍", "😘", "🥰", "😗", "😙")

    @Command("hello")
    fun hello(sender: SlashCommandActor) {
        sender.reply("Hello ${sender.commandEvent().member!!.effectiveName}! ${emojis.random()}")
    }

    @Command("userinfo <member>")
    fun userInfo(sender: SlashCommandActor, member: Member) {
        EmbedBuilder()
            .setTitle("User Info")
            .setDescription(
                """
                **Username**: ${member.effectiveName}
                **Nickname**: ${member.nickname}
                **ID**: ${member.id}
                **Joined Server**: <t:${member.timeJoined.toLocalDateTime().nano / 1000}:F>
                **Roles**: ${member.roles.joinToString(", ") { it.name }}
                **Is Bot**: ${member.user.isBot}
            """.trimIndent()
            )
            .setColor(0x00FF00)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }
}