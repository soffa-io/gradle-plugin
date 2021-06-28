plugins {
    `java-library`
    groovy
    `maven-publish`
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(gradleApi())
    implementation("org.jacoco:org.jacoco.core:0.8.7")
    implementation("org.jacoco:org.jacoco.report:0.8.7")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.5.1")
    api("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.2.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.5.10")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set("SOFFA Gradle Plugin")
                description.set("A concise description of my library")
                url.set("http://www.example.com/library")
                properties.set(mapOf(
                    "myProp" to "value",
                    "prop.with.dots" to "anotherValue"
                ))
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("johnd")
                        name.set("John Doe")
                        email.set("john.doe@example.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://example.com/my-library.git")
                    developerConnection.set("scm:git:ssh://example.com/my-library.git")
                    url.set("http://example.com/my-library/")
                }
            }
        }
    }
    }
}
