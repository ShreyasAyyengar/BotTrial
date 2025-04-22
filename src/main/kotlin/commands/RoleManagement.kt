package dev.shreyasayyengar.commands

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Role
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.jda.actor.SlashCommandActor

object RoleManagement {
    @Command("assign-role <member> <role>")
    @Description("Assigns a role to the user")
    suspend fun assignRole(sender: SlashCommandActor, member: Member, role: Role) {

    }

    @Command("remove-role <member> <role>")
    @Description("Removes a role from the user")
    suspend fun removeRole(sender: SlashCommandActor, member: Member, role: Role) {

    }
}