package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Role
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.jda.actor.SlashCommandActor
import java.awt.Color

object RoleManagementCommands {
    @Command("assign-role <member> <role>")
    @Description("Assigns a role to the user")
    fun assignRole(sender: SlashCommandActor, member: Member, role: Role) {
        if (member.roles.contains(role)) {
            EmbedBuilder()
                .setTitle("Error while executing command...")
                .setDescription("The user already has the role!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            return
        }

        sender.commandEvent().guild!!.addRoleToMember(member, role).queue(
            {
                EmbedBuilder()
                    .setTitle("Role Assigned!")
                    .setDescription("Successfully assigned the role `${role.name}` to ${member.asMention}!")
                    .setColor(Color.GREEN)
                    .build()
                    .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            },
            {
                EmbedBuilder()
                    .setTitle("Error while executing command...")
                    .setDescription("Insufficient permissions to assign the role!")
                    .setColor(Color.RED)
                    .build()
                    .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            }
        )


    }

    @Command("remove-role <member> <role>")
    @Description("Removes a role from the user")
    fun removeRole(sender: SlashCommandActor, member: Member, role: Role) {
        if (!member.roles.contains(role)) {
            EmbedBuilder()
                .setTitle("Error while executing command...")
                .setDescription("The user does not have the role!")
                .setColor(Color.RED)
                .build()
                .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            return
        }

        sender.commandEvent().guild!!.removeRoleFromMember(member, role).queue(
            {
                EmbedBuilder()
                    .setTitle("Role Removed!")
                    .setDescription("Successfully removed the role `${role.name}` from ${member.asMention}!")
                    .setColor(Color.GREEN)
                    .build()
                    .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            },
            {
                EmbedBuilder()
                    .setTitle("Error while executing command...")
                    .setDescription("Insufficient permissions to remove the role!")
                    .setColor(Color.RED)
                    .build()
                    .also { sender.commandEvent().replyEmbeds(it).setEphemeral(true).queue() }
            }
        )
    }
}