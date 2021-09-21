package io.soffa.tools.gradle.service

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpringBootDependencyPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.plugins.apply("io.spring.dependency-management")
//        project.dependencies.add("implementation", project.platform('org.springframework.boot:spring-boot-dependencies:2.5.4'))
  //      project.dependencies.add("implementation", project.platform('org.springframework.cloud:spring-cloud-dependencies:2020.0.3'))
        project.test {
            useJUnitPlatform()
        }
        project.dependencyManagement {
            imports {
                mavenBom "org.springframework.cloud:spring-cloud-dependencies:2020.0.3"
            }
        }
    }


}
