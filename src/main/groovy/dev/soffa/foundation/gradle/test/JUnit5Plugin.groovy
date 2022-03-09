package dev.soffa.foundation.gradle.test


import org.gradle.api.Project

class JUnit5Plugin extends JUnitConfigPlugin {

    @Override
    void apply(Project project) {
        project.dependencies {
            testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
            testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
            testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
            testImplementation("org.mockito:mockito-junit-jupiter:4.3.1")
            testImplementation("com.openpojo:openpojo:0.9.1")
        }
        super.apply(project)
    }
}
