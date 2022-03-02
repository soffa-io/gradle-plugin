package io.soffa.foundation.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class LombokPlugin implements Plugin<Project> {

    void apply(Project project) {
        applyPlugin(project)
    }

    static void applyPlugin(Project project) {
        project.dependencies {
            compileOnly 'org.projectlombok:lombok:1.18.20'
            annotationProcessor 'org.projectlombok:lombok:1.18.20'
            testCompileOnly 'org.projectlombok:lombok:1.18.20'
            testAnnotationProcessor 'org.projectlombok:lombok:1.18.20'
        }
    }


}
