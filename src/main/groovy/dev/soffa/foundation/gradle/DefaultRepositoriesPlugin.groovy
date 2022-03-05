package dev.soffa.foundation.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class DefaultRepositoriesPlugin implements Plugin<Project> {

    void apply(Project project) {

        String prvMvnUrl = project.findProperty("prvMavenUrl") ?: System.getenv("PRV_MAVEN_URL")
        String prvMvnUser = project.findProperty("prvMavenUser") ?: System.getenv("PRV_MAVEN_USER")
        String prvMvnPwd = project.findProperty("prvMavenPassord") ?: System.getenv("PRV_MAVEN_PASSWORD")

        project.repositories {
            mavenLocal()
            mavenCentral()
            maven {
                setUrl("https://oss.sonatype.org/content/groups/public")
            }
            if (prvMvnUrl) {
                prvMvnUrl.split(",").each {repoUrl ->
                    maven {
                        url = repoUrl
                        credentials {
                            username = prvMvnUser
                            password = prvMvnPwd
                        }
                    }
                }

            }
        }
    }


}
