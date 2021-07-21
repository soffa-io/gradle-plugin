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


        String publicationUrl = project.findProperty("publication.url") ?: System.getenv("MVN_PUBLICATION_URL")
        String publicationUser = project.findProperty("publication.username") ?: System.getenv("MVN_PUBLICATION_USER")
        String publicationPassword = project.findProperty("publication.password") ?: System.getenv("MVN_PUBLICATION_PASSWORD")

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
