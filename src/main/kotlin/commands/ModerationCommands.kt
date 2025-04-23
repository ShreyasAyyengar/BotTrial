package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Optional
import revxrsal.commands.annotation.Range
import revxrsal.commands.jda.actor.SlashCommandActor
import java.awt.Color
import java.util.concurrent.TimeUnit

object ModerationCommands {
    @Command("ban <member> <deletion-timeframe> <unit>")
    fun ban(
        sender: SlashCommandActor,
        member: Member,
        @Range(min = 0.0) @Optional deletionTimeframe: Int = 0,
        @Optional unit: TimeUnit? = null
    ) {
        // specified a unit but no deletion timeframe
        if (deletionTimeframe != 0 && unit == null) {
            EmbedBuilder()
                .setTitle("Error while executing command...")
                .setDescription("You must specify a deletion timeframe!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue(); }
            return
        }

        if (unit != null) member.ban(deletionTimeframe, unit).queue()
        else member.ban(0, TimeUnit.SECONDS).queue()

        EmbedBuilder()
            .setTitle("Banned!")
            .setDescription("Successfully banned ${member.asMention}!")
            .setColor(Color.GREEN)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }

    @Command("kick <member>")
    fun kick(sender: SlashCommandActor, member: Member) {
        member.kick().queue()

        EmbedBuilder()
            .setTitle("Kicked!")
            .setDescription("Successfully kicked ${member.asMention}!")
            .setColor(Color.GREEN)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }

    @Command("mute <member> <time> <unit>", "timeout <member> <time> <unit>")
    fun mute(
        sender: SlashCommandActor,
        member: Member,
        @Range(min = 0.0) time: Long,
        unit: TimeUnit
    ) {
        member.timeoutFor(time, unit).queue()

        EmbedBuilder()
            .setTitle("Muted!")
            .setDescription("Successfully muted ${member.asMention}! They will be unmuted in $time ${unit.toString().lowercase()}.")
            .setColor(Color.GREEN)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }
}