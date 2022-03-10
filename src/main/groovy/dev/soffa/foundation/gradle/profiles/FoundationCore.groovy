package dev.soffa.foundation.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class FoundationCore implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.plugins.apply("foundation.java8")
        project.plugins.apply("foundation.test.junit5")

        project.dependencies {
            api("dev.soffa.foundation:foundation-core:${project.property("f4j.version")}")
        }
    }
}
