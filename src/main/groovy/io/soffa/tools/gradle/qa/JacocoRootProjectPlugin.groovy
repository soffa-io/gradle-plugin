package io.soffa.tools.gradle.qa

import org.gradle.api.Plugin
import org.gradle.api.Project

class JacocoRootProjectPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.plugins.apply("java-library")
        project.plugins.apply("org.barfuin.gradle.jacocolog")
        project.tasks.named("check") {
            dependsOn("jacocoAggregatedReport")
        }

    }
}

