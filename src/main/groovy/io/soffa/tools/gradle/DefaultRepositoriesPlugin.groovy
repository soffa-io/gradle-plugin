package io.soffa.tools.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class DefaultRepositoriesPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.repositories {
            mavenLocal()
            mavenCentral()
            maven {
                url = "https://oss.sonatype.org/content/repositories/snapshots/"
            }
            maven {
                url = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            }
        }
    }


}
