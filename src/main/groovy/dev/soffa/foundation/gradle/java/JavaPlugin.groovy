package dev.soffa.foundation.gradle.java

import dev.soffa.foundation.gradle.LombokPlugin
import dev.soffa.foundation.gradle.qa.PmdPlugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.jvm.toolchain.JavaLanguageVersion

class JavaPlugin {

    static void apply(Project project, int version, boolean includeLombok) {
        project.plugins.apply('java')
        project.plugins.apply('java-library')
        if (includeLombok) {
            LombokPlugin.applyPlugin(project)
        }
        project.setProperty("sourceCompatibility",  JavaLanguageVersion.of(version).toString())
        // project.setProperty("targetCompatibility", JavaVersion.VERSION_11)
        if (project.findProperty("soffa.pmd.disabled") != true) {
            new PmdPlugin().apply(project)
        }

        project.compileJava {
            options.encoding = 'UTF-8'
            options.compilerArgs << '-parameters'
            options.warnings = false
            options.deprecation = false
        }

        project.javadoc {
            options.addStringOption("Xdoclint:none", "-quiet")
        }

        boolean isVerbose = Boolean.parseBoolean((project.findProperty("verbose") ?: false).toString()) ||
            Boolean.parseBoolean(System.getenv("CI"));

        project.test {
            testLogging {

                if (isVerbose) {
                    events TestLogEvent.FAILED,
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED,
                        TestLogEvent.STANDARD_OUT

                    exceptionFormat TestExceptionFormat.FULL
                    showExceptions true
                    showCauses true
                    showStackTraces true

                }else {
                    events TestLogEvent.FAILED,
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED
                    exceptionFormat TestExceptionFormat.SHORT
                    showExceptions true
                    showCauses false
                    showStackTraces false
                }

                /*debug {
                    events TestLogEvent.STARTED,
                        TestLogEvent.FAILED,
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED,
                        TestLogEvent.STANDARD_ERROR,
                        TestLogEvent.STANDARD_OUT
                    exceptionFormat TestExceptionFormat.FULL
                }*/
                // info.events = debug.events
                // info.exceptionFormat = debug.exceptionFormat

                afterSuite { desc, result ->
                    if (!desc.parent) { // will match the outermost suite
                        def output = "Results: ${result.resultType} (${result.testCount} tests, ${result.successfulTestCount} passed, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped)"
                        def startItem = '|  ', endItem = '  |'
                        def repeatLength = startItem.length() + output.length() + endItem.length()
                        println('\n' + ('-' * repeatLength) + '\n' + startItem + output + endItem + '\n' + ('-' * repeatLength))
                    }
                }
            }
        }

    }

}
