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


        String publicationUrl = project.findProperty("publishing.url") ?: System.getenv("MVN_PUBLISHING_URL")
        String publicationUser = project.findProperty("publishing.username") ?: System.getenv("MVN_PUBLISHING_USER")
        String publicationPassword = project.findProperty("publishing.password") ?: System.getenv("MVN_PUBLISHING_PASSWORD")

        if (publicationUrl !=null && publicationUser != null && publicationPassword!=null) {
            println("Configuration maven publication: ${publicationUrl} / ${publicationUser}")
            project.publishing.repositories {
                maven {
                    url = publicationUrl
                    credentials {
                        username = publicationUser
                        password = publicationPassword
                    }
                }
            }
        }
    }

}
