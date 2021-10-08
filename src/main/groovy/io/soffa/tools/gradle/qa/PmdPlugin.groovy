package io.soffa.tools.gradle.qa

import org.gradle.api.Plugin
import org.gradle.api.Project

class PmdPlugin implements Plugin<Project> {

    void apply(Project project) {
        def configDir = "${project.rootDir}/config"
        File configFile = new File("$configDir/qa/pmd.xml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs()
        }
        if (!configFile.exists() || (System.currentTimeMillis() - configFile.lastModified() > 1000 * 60)) {
            configFile.write('''<?xml version="1.0"?>
<ruleset name="SGABS"
    xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 https://pmd.sourceforge.io/ruleset_2_0_0.xsd">

    <rule ref="category/java/bestpractices.xml">
        <exclude name="JUnitTestContainsTooManyAsserts"/>
        <exclude name="ConstantsInInterface"/>
        <exclude name="JUnitAssertionsShouldIncludeMessage" />
        <exclude name="JUnitTestsShouldIncludeAssert" />

    </rule>
    <rule ref="category/java/codestyle.xml">
        <exclude name="MethodArgumentCouldBeFinal"/>
        <exclude name="LocalVariableCouldBeFinal"/>
        <exclude name="ControlStatementBraces"/>
        <exclude name="OnlyOneReturn"/>
        <exclude name="ConfusingTernary"/>
        <exclude name="AtLeastOneConstructor"/>
        <exclude name="CommentDefaultAccessModifier"/>
        <exclude name="PrematureDeclaration"/>
        <exclude name="ShortVariable"/>
        <exclude name="LongVariable"/>
        <exclude name="DefaultPackage"/>
        <exclude name="ShortClassName"/>
        <exclude name="ShortMethodName"/>
        <exclude name="LinguisticNaming"/>
    </rule>
    
    <rule ref="category/java/codestyle.xml/ClassNamingConventions">
        <properties>
            <property name="classPattern" value="[A-Z][a-zA-Z0-9]*" />
            <property name="abstractClassPattern" value="[A-Z][a-zA-Z0-9]*" />
            <property name="interfacePattern" value="[A-Z][a-zA-Z0-9]*" />
            <property name="enumPattern" value="[A-Z][a-zA-Z0-9]*" />
            <property name="annotationPattern" value="[A-Z][a-zA-Z0-9]*" />
            <property name="utilityClassPattern" value="[A-Z][a-zA-Z0-9]+(Utils?|Helper|Constants|Factory|Holder|Support)" />
        </properties>
    </rule>
    
    <rule ref="category/java/design.xml">
        <exclude name="UseUtilityClass"/>
        <exclude name="ExcessiveImports"/>
        <exclude name="AvoidCatchingGenericException"/>
        <exclude name="LawOfDemeter"/>
        <exclude name="NcssCount"/>
        <exclude name="UseUtilityClass"/>
        <exclude name="NPathComplexity"/>
        <exclude name="AvoidRethrowingException"/>
        <exclude name="DataClass"/>
        <exclude name="TooManyFields"/>
        <exclude name="TooManyMethods"/>
    </rule>
    <rule ref="category/java/documentation.xml">
        <exclude name="CommentRequired"/>
        <exclude name="UncommentedEmptyConstructor"/>
        <exclude name="CommentSize"/>
    </rule>
    <rule ref="category/java/errorprone.xml">
        <exclude name="DataflowAnomalyAnalysis"/>
        <exclude name="BeanMembersShouldSerialize"/>
        <exclude name="AssignmentInOperand"/>
        <exclude name="UseLocaleWithCaseConversions"/>
    </rule>
    <rule ref="category/java/multithreading.xml">
        <exclude name="DoNotUseThreads" />
        <exclude name="UseConcurrentHashMap" />
    </rule>
    <rule ref="category/java/performance.xml">
        <exclude name="AvoidInstantiatingObjectsInLoops"/>
    </rule>
    <rule ref="category/java/security.xml">
    </rule>
</ruleset>''')
        }
        if (project.subprojects.isEmpty()) {
            internalApply(project, configDir)
        }else {
            project.subprojects { Project it ->
                internalApply(it, configDir)
            }
        }
    }

    private static void internalApply(Project project, String configDir) {
        project.plugins.apply("pmd")
        project.pmd {
            consoleOutput = true
            toolVersion = "6.31.0"
            rulesMinimumPriority = 5
            //ruleSets = ["category/java/errorprone.xml", "category/java/bestpractices.xml"]
            ruleSetConfig = project.resources.text.fromFile("$configDir/qa/pmd.xml")
            ruleSets = []
        }
    }
}
