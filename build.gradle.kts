import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    application
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
    id("com.gradleup.shadow") version "9.0.0-beta11"
}

group = "dev.shreyasayyengar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")  // Kotlin Serialization
    implementation("net.dv8tion:JDA:5.3.0")                                   // Java Discord API
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.11")              // Lamp Common
    implementation("io.github.revxrsal:lamp.jda:4.0.0-rc.11")                 // Lamp JDA
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.3.0")       // MongoDB Driver
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")                 // Dotenv
}

tasks {
    build { dependsOn(shadowJar) }

    shadowJar {
        archiveFileName.set("BotTrial.jar")
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(22)
    }

    compileKotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_22)
    }
}
application { mainClass.set("dev.shreyasayyengar.commissionsmanager.MainKt") }

kotlin {
    jvmToolchain(22)
    compilerOptions { javaParameters = true } // Lamp CMD Framework
}