package dev.soffa.foundation.gradle.test

import org.gradle.api.Project

class CucumberPlugin extends JUnit5Plugin {

    @Override
    void apply(Project project) {

        super.apply(project);

        project.dependencies {
            testImplementation "io.cucumber:cucumber-java8:7.2.3"
            testImplementation "io.cucumber:cucumber-java:7.2.3"
            testImplementation "io.cucumber:cucumber-junit:7.2.3"
            testImplementation("io.cucumber:cucumber-junit-platform-engine:7.2.3") {
                because("we want to use Cucumber with JUnit 5")
            }
            testImplementation("io.cucumber:cucumber-picocontainer:7.2.3") {
                because("we want to use dependency injection in our Cucumber tests")
            }
            testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.7.1") {
                exclude group: "org.hamcrest", module: "hamcrest-core"
            }
        }

    }
}
