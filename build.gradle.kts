import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group = "org.remiluka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    named<ShadowJar>("shadowJar") {
        configurations = listOf(project.configurations.shadow.get())
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    shadow("com.github.Revxrsal.Lamp:common:3.1.7")
    shadow("com.github.Revxrsal.Lamp:bukkit:3.1.7")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(20))
}