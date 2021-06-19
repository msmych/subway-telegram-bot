import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaTelegramBotApiVersion: String by project

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks

compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

dependencies {
    implementation("com.github.pengrad:java-telegram-bot-api:$javaTelegramBotApiVersion")
    implementation(kotlin("stdlib-jdk8"))
}

application {
    mainClass.set("digital.matvey.subwaybot.SubwayNnBotAppKt")
}