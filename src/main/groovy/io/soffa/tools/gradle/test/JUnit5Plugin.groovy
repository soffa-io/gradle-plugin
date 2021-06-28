package io.soffa.tools.gradle.test


import org.gradle.api.Project

class JUnit5Plugin extends JUnitConfigPlugin {

    @Override
    void apply(Project project) {
        project.dependencies {
            testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
            testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
            testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.1")
            testImplementation("org.mockito:mockito-junit-jupiter:3.7.7")
            testImplementation("com.openpojo:openpojo:0.8.13")
        }
        Object.apply(project)
    }
}
