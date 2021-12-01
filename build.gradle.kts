import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "me.sclip"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    jcenter()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.github.kwebio:kweb-core:0.10.17")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("com.yahoofinance-api:YahooFinanceAPI:3.15.0")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}