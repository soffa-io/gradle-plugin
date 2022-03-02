package io.soffa.foundation.gradle.tasks


import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

abstract class RenameTask extends DefaultTask{

    @TaskAction
    def rename() {

        def rootDir = super.getProject().getRootDir()
        def settingsFile = new File("$rootDir/settings.gradle.kts")

        String name = super.getProject().property("root")

        def api = new File("$rootDir/app-api")
        def core = new File("$rootDir/app-core")
        def service = new File("$rootDir/app-service")

        if (!settingsFile.exists()) {
            throw new GradleException("Unable to locate settings.gradle.kts")
        }
        if (!api.exists()) {
            throw new GradleException("Folder app-api not found (project already initialized once?)")
        }
        if (!core.exists()) {
            throw new GradleException("Folder app-core not found (project already initialized once?)")
        }
        if (!service.exists()) {
            throw new GradleException("Folder app-service not found (project already initialized once?)")
        }

        ant.replace(file: settingsFile, token: "foundation-starter", value: name)
        ant.replace(file: settingsFile, token: ":app-", value: ":${name}-")
        ant.replace(file: new File(service, "build.gradle.kts"), token: ":app-", value: ":${name}-")
        ant.replace(file: new File(core, "build.gradle.kts"), token: ":app-", value: ":${name}-")


        if (!api.renameTo("${name}-api")) {
            throw new GradleException("Failed to rename ${api.name}")
        }
        if (!core.renameTo("${name}-core")) {
            throw new GradleException("Failed to rename ${core.name}")
        }
        if (!service.renameTo("${name}-service")) {
            throw new GradleException("Failed to rename ${service.name}")
        }

        io.soffa.foundation.gradle.Logger.success("Project initialzed successfully with name: $name")
    }

}
