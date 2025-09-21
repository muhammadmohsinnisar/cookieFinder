plugins {
    kotlin("jvm") version "2.2.10"
    application
}

group = "com.mohsin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.mohsin.cookiefinder.MainKt")
    applicationName = "cookieFinder"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.mohsin.cookiefinder.MainKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

kotlin {
    jvmToolchain(17)
}