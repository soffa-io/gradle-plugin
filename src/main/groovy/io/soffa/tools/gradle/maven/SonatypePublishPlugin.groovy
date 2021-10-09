package io.soffa.tools.gradle.maven

import org.gradle.api.Plugin
import org.gradle.api.Project

class SonatypePublishPlugin implements Plugin<Project> {

    static final String NEXUS_PUBLISH_PLUGIN = "io.github.gradle-nexus.publish-plugin"

    void apply(Project project) {
        project.plugins.apply(NEXUS_PUBLISH_PLUGIN)
        project.plugins.apply("signing")
        project.nexusPublishing {
            repositories {
                sonatype {
                    // stagingProfileId.set(System.getenv("SONATYPE_STAGING_PROFILE_ID"))
                    username = project.property("ossrhUsername")
                    password = project.property("ossrhPassword")
                }
            }
        }
    }

}
