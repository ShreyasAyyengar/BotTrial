package dev.shreyasayyengar

import dev.shreyasayyengar.commands.MiscCommands
import dev.shreyasayyengar.commands.ModerationCommands
import dev.shreyasayyengar.commands.RoleManagement
import dev.shreyasayyengar.listeners.HousekeepingEventListener
import dev.shreyasayyengar.util.Credentials
import dev.shreyasayyengar.util.LocaleManager
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import revxrsal.commands.Lamp
import revxrsal.commands.jda.JDALamp
import revxrsal.commands.jda.JDAVisitors
import revxrsal.commands.jda.actor.SlashCommandActor

object DiscordBot {
    val discordBot: JDA = JDABuilder.createDefault(Credentials.BOT_TOKEN)
        .addEventListeners(HousekeepingEventListener)
        .setEnabledIntents(GatewayIntent.entries)
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .build()
        .awaitReady()
    val jdaLamp: Lamp<SlashCommandActor> = JDALamp.builder<SlashCommandActor>()
        .build()
        .apply {
            register(
                MiscCommands,
                RoleManagement,
                ModerationCommands
            )
            accept(JDAVisitors.slashCommands(discordBot))
        }

    init {
        println("[BotTrial] Started!")
        LocaleManager
    }
}