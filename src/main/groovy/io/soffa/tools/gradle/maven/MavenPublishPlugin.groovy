package io.soffa.tools.gradle.maven

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication

class MavenPublishPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.plugins.apply('maven-publish')

        project.publishing.publications {
            maven(MavenPublication) {
                from project.components.java
                groupId = project.property("group")
                artifactId = project.name
            }
        }

        String mvnPublishingUrl = project.findProperty("mavenPublishingUrl") ?: System.getenv("MAVEN_PUBLISHING_URL")
        String mvnPublishingUser = project.findProperty("mavenPublishingUser") ?: System.getenv("MAVEN_PUBLISHING_USER")
        String mvnPublishingPwd = project.findProperty("mavenPublishingPassword") ?: System.getenv("MAVEN_PUBLISHING_PASSWORD")

        if (mvnPublishingUrl != null) {
            project.publishing.repositories {
                maven {
                    url = mvnPublishingUrl
                    credentials {
                        username = mvnPublishingUser
                        password = mvnPublishingPwd
                    }
                }
            }
        }
    }

}
