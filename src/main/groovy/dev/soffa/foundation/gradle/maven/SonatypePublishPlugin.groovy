package dev.soffa.foundation.gradle.maven

import org.gradle.api.Plugin
import org.gradle.api.Project

class SonatypePublishPlugin implements Plugin<Project> {

    public static final String NEXUS_PUBLISH_PLUGIN = "io.github.gradle-nexus.publish-plugin"

    void apply(Project project) {
        // project.plugins.apply("signing")
        project.plugins.apply(NEXUS_PUBLISH_PLUGIN)
        project.nexusPublishing {
            repositories {
                sonatype {
                    username = project.findProperty("ossrhUsername")
                    password = project.findProperty("ossrhPassword")
                    nexusUrl.set(new URI("https://s01.oss.sonatype.org/service/local/"))
                    snapshotRepositoryUrl.set(new URI("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
                }
            }
        }
    }

}
