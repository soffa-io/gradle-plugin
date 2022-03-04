package io.soffa.foundation.gradle.spring

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpringBootTracingPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.dependencies {
            implementation("org.springframework.cloud:spring-cloud-starter-zipkin")
        }
    }

}
