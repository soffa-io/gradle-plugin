package io.soffa.foundation.gradle

import io.soffa.foundation.gradle.tasks.RenameTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class ToolingPlugin implements Plugin<Project> {

    void apply(Project project) {
        applyPlugin(project)
    }

    static void applyPlugin(Project project) {
        project.tasks.register('soffa-init', RenameTask.class)
    }


}
