package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.entities.Member
import revxrsal.commands.annotation.Command
import revxrsal.commands.jda.actor.SlashCommandActor

object ModerationCommands {
    @Command("ban <member>")
    suspend fun ban(sender: SlashCommandActor, member: Member) {

    }

    @Command("kick <member>")
    suspend fun kick(sender: SlashCommandActor, member: Member) {

    }

    @Command("mute <member> <minutes>")
    suspend fun mute(sender: SlashCommandActor, member: Member, minutes: Int) {

    }
}