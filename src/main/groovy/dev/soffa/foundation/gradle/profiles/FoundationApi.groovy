package dev.soffa.foundation.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class FoundationApi implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.plugins.apply("foundation.java8")
        project.plugins.apply("foundation.test.junit5")
        project.dependencies {
            runtimeOnly("com.intuit.karate:karate-junit5:1.2.0.RC4")
            api("dev.soffa.foundation:foundation-api:${project.property("foundation.version")}")
        }
    }
}
