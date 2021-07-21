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
    }

}
