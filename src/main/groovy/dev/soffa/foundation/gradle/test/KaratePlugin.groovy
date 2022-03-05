package dev.soffa.foundation.gradle.test

import org.gradle.api.Project

class KaratePlugin extends JUnit5Plugin {

    @Override
    void apply(Project project) {
        super.apply(project)

        project.dependencies {
            compileOnly("com.intuit.karate:karate-junit5:1.2.0.RC4")
            testImplementation("com.intuit.karate:karate-junit5:1.2.0.RC4")
            testImplementation("com.intuit.karate:karate-apache:1.2.0.RC4")
            testImplementation("com.intuit.karate:karate-mock-servlet:1.2.0.RC4")
            // testImplementation("com.intuit.karate:karate-mock-servlet:1.2.0.RC4")
        }

    }
}
