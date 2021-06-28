package io.soffa.tools.gradle.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

class JUnitConfigPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        if (!System.getenv("SOFA_JENKINS")) {
            project.test {
                useJUnitPlatform()
            }
        } else {


            project.test {
                useJUnitPlatform()
                testLogging {
                    // set options for log level LIFECYCLE
                    events TestLogEvent.FAILED,
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED,
                        TestLogEvent.STANDARD_OUT
                    exceptionFormat TestExceptionFormat.FULL
                    showExceptions true
                    showCauses true
                    showStackTraces true

                    // set options for log level DEBUG and INFO
                    debug {
                        events TestLogEvent.STARTED,
                            TestLogEvent.FAILED,
                            TestLogEvent.PASSED,
                            TestLogEvent.SKIPPED,
                            TestLogEvent.STANDARD_ERROR,
                            TestLogEvent.STANDARD_OUT
                        exceptionFormat TestExceptionFormat.FULL
                    }
                    info.events = debug.events
                    info.exceptionFormat = debug.exceptionFormat

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
}
