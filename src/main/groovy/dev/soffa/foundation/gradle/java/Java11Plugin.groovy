package dev.soffa.foundation.gradle.java


import org.gradle.api.Plugin
import org.gradle.api.Project

class Java11Plugin implements Plugin<Project> {

    void apply(Project project) {
        JavaPlugin.apply(project, 11, true)
    }

}
