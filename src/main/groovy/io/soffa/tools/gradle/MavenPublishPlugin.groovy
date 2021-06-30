package io.soffa.tools.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication

class MavenPublishPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.plugins.apply('maven-publish')

        def releaseVersion = project.version.toString()
        if (project.findProperty("release") != null) {
            releaseVersion = releaseVersion.replace("-SNAPSHOT", "")
        } else if (project.findProperty("snapshot") != null && !releaseVersion.endsWith("-SNAPSHOT")) {
            releaseVersion = releaseVersion + "-SNAPSHOT"
        }

        def nexusServer = project.findProperty("nexusServer")

        project.publishing {

            if (nexusServer != null) {
                repositories {
                    maven {
                        name = "nexus-server"
                        if (releaseVersion.endsWith("-SNAPSHOT")) {
                            url = "${nexusServer}/repository/maven-snapshots/"
                        } else {
                            url = "${nexusServer}/repository/maven-releases/"
                        }
                        allowInsecureProtocol = true
                        credentials {
                            username = project.findProperty("mvnUsername")
                            password = project.findProperty("mvnPassword")
                        }
                    }
                }
            }

            publications {
                maven(MavenPublication) {
                    from project.components.java
                    groupId = project.property("group")
                    artifactId = project.name
                    version = releaseVersion
                }
            }
        }


    }

}
