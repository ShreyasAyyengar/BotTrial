package dev.shreyasayyengar.commands

import dev.shreyasayyengar.util.MongoService
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import revxrsal.commands.annotation.Command
import revxrsal.commands.jda.actor.SlashCommandActor
import java.awt.Color

object PointsCommands {
    @Command("add-points <member> <amount>")
    suspend fun addPoints(sender: SlashCommandActor, member: Member, amount: Int) {
        val snowflakeId = member.idLong
        MongoService.addPoint(snowflakeId, amount)

        EmbedBuilder()
            .setTitle("Points Added!")
            .setDescription("Successfully added $amount points to ${member.asMention}!")
            .setColor(Color.GREEN)
            .build()
            .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
    }

    @Command("get-points <member>")
    suspend fun getPoints(sender: SlashCommandActor, member: Member) {
        val snowflakeId = member.idLong
        val points = MongoService.getPoints(snowflakeId)

        if (points == null) {
            EmbedBuilder()
                .setTitle("Points for ${member.asMention}!")
                .setDescription("They have no points!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
        } else {
            EmbedBuilder()
                .setTitle("Points for ${member.asMention}!")
                .setDescription("They have $points points!")
                .setColor(Color.GREEN)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
        }
    }
}