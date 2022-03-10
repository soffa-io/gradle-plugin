package dev.soffa.foundation.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class FoundationService implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.plugins.apply("foundation.java8")
        project.plugins.apply("foundation.springboot")

        project.dependencies {
            implementation("dev.soffa.foundation:foundation-starter:${project.property("f4j.version")}")
            testImplementation("dev.soffa.foundation:foundation-starter-test:${project.property("f4j.version")}")
        }
    }
}

