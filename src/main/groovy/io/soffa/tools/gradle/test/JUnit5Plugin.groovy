package io.soffa.tools.gradle.test


import org.gradle.api.Project

class JUnit5Plugin extends JUnitConfigPlugin {

    @Override
    void apply(Project project) {
        project.dependencies {
            testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
            testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
            testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
            testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")
            testImplementation("com.openpojo:openpojo:0.9.1")

            testImplementation("ch.qos.logback:logback-classic:1.5.5")
            testImplementation("ch.qos.logback:logback-classic:1.5.5")
        }
        super.apply(project)
    }
}
