package io.soffa.tools.gradle.java


import org.gradle.api.Plugin
import org.gradle.api.Project

class Java21Plugin implements Plugin<Project> {

    void apply(Project project) {
        JavaPlugin.apply(project, 21, false)
    }

}
