package io.soffa.tools.gradle.test

import org.gradle.api.Project

class KaratePlugin extends JUnit5Plugin {

    @Override
    void apply(Project project) {
        Object.apply(project)

        project.dependencies {
            compileOnly("com.intuit.karate:karate-junit5:0.9.6")
            testImplementation("com.intuit.karate:karate-junit5:0.9.6")
            testImplementation("com.intuit.karate:karate-apache:0.9.6")
            testImplementation("com.intuit.karate:karate-mock-servlet:0.9.6")
            // testImplementation("com.intuit.karate:karate-mock-servlet:0.9.6")
        }

    }
}
