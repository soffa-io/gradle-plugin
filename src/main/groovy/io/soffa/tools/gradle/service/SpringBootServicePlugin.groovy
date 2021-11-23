package io.soffa.tools.gradle.service

import org.gradle.api.Project

class SpringBootServicePlugin extends SpringBootDependencyPlugin {

    void apply(Project project) {
        project.plugins.apply("org.springframework.boot")
        super.apply(project)
        // project.dependencies.add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
        project.jar {
            enabled = false
        }
        project.bootJar {
            classifier = 'application'
        }
    }

}
