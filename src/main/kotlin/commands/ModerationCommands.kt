package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.exceptions.HierarchyException
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Named
import revxrsal.commands.annotation.Optional
import revxrsal.commands.annotation.Range
import revxrsal.commands.jda.actor.SlashCommandActor
import revxrsal.commands.jda.annotation.CommandPermission
import java.awt.Color
import java.util.concurrent.TimeUnit

@CommandPermission(Permission.ADMINISTRATOR)
object ModerationCommands {
    @Command("ban <member> <deletion-timeframe> <unit>")
    fun ban(
        sender: SlashCommandActor,
        member: Member,
        @Named("deletion-timeframe") @Range(min = 0.0) @Optional deletionTimeframe: Int = 0,
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

        try {
            if (unit != null) member.ban(deletionTimeframe, unit).queue()
            else member.ban(0, TimeUnit.SECONDS).queue()
        } catch (_: HierarchyException) {
            EmbedBuilder()
                .setTitle("Insufficient permissions to ban ${member.effectiveName}!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            return
        }

        EmbedBuilder()
            .setTitle("Banned!")
            .setDescription("Successfully banned ${member.asMention}!")
            .setColor(Color.GREEN)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }

    @Command("kick <member>")
    fun kick(sender: SlashCommandActor, member: Member) {
        try {
            member.kick().queue()
        } catch (_: HierarchyException) {
            EmbedBuilder()
                .setTitle("Insufficient permissions to kick ${member.effectiveName}!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            return
        }

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
        try {
            member.timeoutFor(time, unit).queue()
        } catch (_: IllegalArgumentException) {
            EmbedBuilder()
                .setTitle("The time you provided was so short, it's already in the past!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            return
        } catch (_: HierarchyException) {
            EmbedBuilder()
                .setTitle("Insufficient permissions to timeout ${member.effectiveName}!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            return
        }

        EmbedBuilder()
            .setTitle("Muted!")
            .setDescription("Successfully muted ${member.asMention}! They will be unmuted in $time ${unit.toString().lowercase()}.")
            .setColor(Color.GREEN)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }
}