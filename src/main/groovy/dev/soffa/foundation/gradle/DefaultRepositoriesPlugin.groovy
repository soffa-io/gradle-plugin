package dev.soffa.foundation.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class DefaultRepositoriesPlugin implements Plugin<Project> {

    void apply(Project project) {

        String prvMvnUrl = project.findProperty("privateMavenUrl") ?: System.getenv("PRIVATE_MAVEN_URL") ?: project.findProperty("mavenUrl") ?: System.getenv("MAVEN_URL")
        String prvMvnUser = project.findProperty("privateMavenUser") ?: System.getenv("PRIVATE_MAVEN_USER") ?: project.findProperty("mavenUser") ?: System.getenv("MAVEN_USER")
        String prvMvnPwd = project.findProperty("privateMavenPassord") ?: System.getenv("PRIVATE_MAVEN_PASSWORD") ?: project.findProperty("mavenPassord") ?: System.getenv("MAVEN_PASSWORD")

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
                        if (prvMvnUser!=null && !prvMvnUser.isEmpty()) {
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


}
