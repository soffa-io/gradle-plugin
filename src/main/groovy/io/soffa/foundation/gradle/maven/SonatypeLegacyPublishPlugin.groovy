package io.soffa.foundation.gradle.maven

import org.gradle.api.Plugin
import org.gradle.api.Project

public class SonatypeLegacyPublishPlugin implements Plugin<Project> {

    public static final String NEXUS_PUBLISH_PLUGIN = "io.github.gradle-nexus.publish-plugin"

    void apply(Project project) {
        project.plugins.apply("signing")
        project.plugins.apply(NEXUS_PUBLISH_PLUGIN)
        project.nexusPublishing {
            repositories {
                sonatype {
                    // stagingProfileId.set(System.getenv("SONATYPE_STAGING_PROFILE_ID"))
                    username = project.findProperty("ossrhUsername")
                    password = project.findProperty("ossrhPassword")
                }
            }
        }
    }

}
