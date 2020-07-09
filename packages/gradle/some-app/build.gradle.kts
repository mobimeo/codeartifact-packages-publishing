plugins {
    application
    kotlin("jvm") version "1.3.72"
    `maven`
}

group = "com.reachnow"
version = "0.0.1-SNAPSHOT"

val ktorVersion by extra("1.3.2")
val logbackVersion by extra("1.2.3")

val mainClass by extra("io.ktor.server.netty.EngineMain")

application {
    mainClassName = mainClass
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
    mavenCentral()
    // inspired by
    // https://stackoverflow.com/questions/12749225/where-to-put-gradle-configuration-i-e-credentials-that-should-not-be-committe/35628079#35628079 @ 2020 06 18
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/reach-now/codeartifact-packages-publishing/")
        val mavenUser: String by project
        val mavenPassword: String by project
        credentials {
            username = "${mavenUser}"
            password = "${mavenPassword}"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.reachnow:some-library:0.0.1-SNAPSHOT-2020-06-23--22-26-38-994")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    testCompile ("io.ktor:ktor-server-test-host:$ktorVersion")

}


