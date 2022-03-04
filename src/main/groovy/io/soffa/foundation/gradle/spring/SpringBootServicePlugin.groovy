package io.soffa.foundation.gradle.spring

import org.gradle.api.Project

class SpringBootServicePlugin extends SpringBootDependencyPlugin {

    void apply(Project project) {
        super.apply(project)
        project.plugins.apply("org.springframework.boot")
        project.plugins.apply("com.google.cloud.tools.jib")
        // project.dependencies.add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
        project.jar {
            enabled = false
        }
        /*
        project.bootJar {
            classifier = 'application'
        }

        project.configurations {
            [apiElements, runtimeElements].each {
                it.outgoing.artifacts.removeIf { it.buildDependencies.getDependencies(null).contains(jar) }
                it.outgoing.artifact(project.bootJar)
            }
        }*/

    }

}
