package io.soffa.foundation.gradle.spring

import org.gradle.api.Project

class SpringBootVaultPlugin extends SpringBootDependencyPlugin {

    void apply(Project project) {
        super.apply(project)
        project.dependencies {
            implementation('org.springframework.cloud:spring-cloud-starter-vault-config')
        }

    }

}
