package io.soffa.tools.gradle.qa

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestCoverageL0 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.0)
    }
}

class TestCoverageL1 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.1)
    }
}

class TestCoverageL2 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.2)
    }
}

class TestCoverageL3 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.3)
    }
}

class TestCoverageL4 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.4)
    }
}

class TestCoverageL5 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.5)
    }
}

class TestCoverageL6 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.6)
    }
}

class TestCoverageL7 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.7)
    }
}

class TestCoverageL8 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.8)
    }
}

class TestCoverageL85 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.85)
    }
}

class TestCoverageL9 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.9)
    }
}

class TestCoverageL95 implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.95)
    }
}

class TestCoverageLX implements Plugin<Project> {
    void apply(Project project) {
        JacocoSupport.applyTestCoverage(project, 0.0, 0.99)
    }
}


class JacocoSupport {

    static void applyTestCoverage(Project project, String coverage) {
        applyTestCoverage(project, 0.0, new BigDecimal(coverage.replace("l", "0.")))
    }

    static void applyTestCoverage(Project project, BigDecimal _, BigDecimal linesCoverage) {
        project.plugins.apply("jacoco")
        project.jacocoTestReport {
            reports {
                xml.enabled true
                xml.destination project.file("${project.buildDir}/coverage.xml")
                html.enabled true
                html.destination project.file("${project.buildDir}/jacocoHtml")
            }
        }

        project.jacocoTestCoverageVerification {
            violationRules {
                rule {
                    limit {
                        minimum = linesCoverage
                        counter = "LINE" // BRANCH CLASS COMPLEXITY INSTRUCTION  LINE  METHOD
                        value = "COVEREDRATIO" // COVEREDCOUNT COVEREDRATIO  MISSEDCOUNT  MISSEDRATIO  TOTALCOUNT
                    }
                }
            }
            dependsOn("jacocoTestReport")
        }

        project.jacoco {
            toolVersion = "0.8.5"
        }

        /*project.test {
            finalizedBy("jacocoTestReport")
        }*/

        project.check {
            dependsOn("jacocoTestCoverageVerification")
        }
    }
}

