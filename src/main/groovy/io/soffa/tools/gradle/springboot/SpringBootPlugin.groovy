package io.soffa.tools.gradle.springboot


import org.gradle.api.Project

class SpringBootPlugin extends SpringBootConfigPlugin {

    @Override
    void apply(Project project) {
        super.apply(project);

        project.plugins.apply("org.springframework.boot")
        /*project.dependencies {
            testImplementation("org.springframework.boot:spring-boot-starter-test") {
                exclude group: "org.junit.vintage", module: "junit-vintage-engine"
                exclude group: "com.vaadin.external.google"
            }
        }
         */
        project.dependencies.add("implementation", "io.soffa.platform:soffa-platform-springboot-web:${project.property("soffa-platform-springboot.version")}")

        project.configurations {
            [it.apiElements, it.runtimeElements].each {
                it.outgoing.artifacts.removeIf { it.buildDependencies.getDependencies(null).contains(project.tasks.jar) }
                it.outgoing.artifact(project.tasks.bootJar)
            }
        }
        //}
    }

}
