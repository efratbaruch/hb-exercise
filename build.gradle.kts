plugins {
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.seleniumhq.selenium:selenium-java:4.17.0")
    testImplementation("org.testng:testng:7.9.0")
}

tasks.test {
    useTestNG()
}

kotlin {
    jvmToolchain(21)
}