package io.soffa.tools.gradle.service

import org.gradle.api.Project

class SpringBootDataPlugin extends SpringBootDependencyPlugin {

    void apply(Project project) {
        super.apply(project)
        project.dependencies {
            implementation('org.springframework.boot:spring-boot-starter-data-jpa')
            testImplementation("com.h2database:h2:1.4.200")
        }

    }

}
