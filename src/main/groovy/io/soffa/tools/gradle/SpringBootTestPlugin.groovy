package io.soffa.tools.gradle


import org.gradle.api.Project

class SpringBootTestPlugin extends SpringBootPlatformTestPlugin {

    @Override
    void apply(Project project) {
        Object.apply(project)
        project.plugins.apply("soffa.test.karate")
        project.dependencies {
            testImplementation("org.springframework.boot:spring-boot-starter-web")
            testImplementation("org.springframework.boot:spring-boot-starter-test") {
                exclude group: "org.junit.vintage", module: "junit-vintage-engine"
                exclude group: "com.vaadin.external.google"
            }
        }
    }
}
