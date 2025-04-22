package dev.shreyasayyengar.util

object Credentials {
    val BOT_TOKEN: String by lazy { System.getenv("BOT_TOKEN") }
    val MONGO_URL: String by lazy { System.getenv("MONGO_URL") }
}