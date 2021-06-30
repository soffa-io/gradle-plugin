package io.soffa.tools.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project

class JUnitConfigPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.test {
            useJUnitPlatform()
        }
    }

}
