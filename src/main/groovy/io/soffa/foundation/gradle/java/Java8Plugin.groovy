package io.soffa.foundation.gradle.java


import org.gradle.api.Plugin
import org.gradle.api.Project

class Java8Plugin implements Plugin<Project> {

    void apply(Project project) {
        JavaPlugin.apply(project, 8, true)
    }

}
