plugins {
    `java-library`
    groovy
    signing
    `maven-publish`
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    compileOnly(gradleApi())
    implementation("org.jacoco:org.jacoco.core:0.8.7")
    implementation("org.jacoco:org.jacoco.report:0.8.7")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.5.1")
    api("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.2.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.5.20")
}

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set("SOFFA Gradle Plugin")
                description.set("A gradle plugin with useful plugins.")
                url.set("https://github.com/soffa-io/soffa-gradle-plugin")
                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("startup-station")
                        name.set("Startup Station")
                        email.set("soffa@startupstation.co")
                    }
                }
                scm {
                    connection.set("scm:git:git://soffa-io/soffa-gradle-plugin.git")
                    developerConnection.set("scm:git:ssh://soffa-io/soffa-gradle-plugin.git")
                    url.set("https://github.com/soffa-io/soffa-gradle-plugin")
                }
            }
        }
    }
}

ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
ext["sonatypeStagingProfileId"] = ""
ext["signing.keyId"] = System.getenv("SIGNING_KEYID")
ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
ext["signing.secretKeyRingFile"] = property("signing.secretKeyRingFile")


nexusPublishing {
    repositories {
        sonatype {
            // stagingProfileId.set(System.getenv("SONATYPE_STAGING_PROFILE_ID"))
            username.set("${property("ossrhUsername")}")
            password.set("${property("ossrhPassword")}")
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}
