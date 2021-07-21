package io.soffa.tools.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class DefaultRepositoriesPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.repositories {
            mavenLocal()
            mavenCentral()
            maven {
                setUrl("https://oss.sonatype.org/content/groups/public")
            }
            maven {
                setUrl("https://oss.sonatype.org/content/groups/staging")
            }
        }
    }


}
