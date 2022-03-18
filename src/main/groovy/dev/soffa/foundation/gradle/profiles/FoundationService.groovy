package dev.soffa.foundation.gradle.profiles

import org.gradle.api.Plugin
import org.gradle.api.Project

class FoundationService implements Plugin<Project> {

    @Override
    void apply(Project project) {
        configure(project, false)
    }

    void configure(Project project, boolean kotlin) {

        if (kotlin) {
            project.plugins.apply("foundation.kotlin")
        } else {
            project.plugins.apply("foundation.java8")
        }
        project.plugins.apply("foundation.springboot")

        String[] modules = (project.findProperty("foundation.modules") ?: "").toString().split(",")

        String coverage = (project.findProperty("foundation.qa.coverage") ?: "").toString()
        if (!coverage.isEmpty()) {
            project.plugins.apply("foundation.qa.coverage.${coverage}")
        }


        project.dependencies {
            implementation("dev.soffa.foundation:foundation-starter:${project.property("foundation.version")}")
            testImplementation("dev.soffa.foundation:foundation-starter-test:${project.property("foundation.version")}")
            for (String module: modules) {
                if (!module.isEmpty()) {
                    implementation("dev.soffa.foundation:foundation-starter-${module}:${project.property("foundation.version")}")
                }
            }
        }

        /*
        project.springBoot {
            mainClass = "dev.soffa.foundation.Foundation"
        }
        */


    }
}

