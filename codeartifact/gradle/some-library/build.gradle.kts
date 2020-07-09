group = "com.reachnow"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.3.61"
    `maven-publish`
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.12")
}

publishing {
    publications {
        create<MavenPublication>("default") { 
            from(components["java"])
        }
    }
    repositories {
        maven {
            val reachnowRepoUrl: String by project
            url = uri(reachnowRepoUrl)
            credentials {
              username = "aws"
              password = System.getenv("CODEARTIFACT_AUTH_TOKEN")
            }
        }
    }
}
