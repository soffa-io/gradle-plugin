## soffa/gradle-plugin

## Comment activer ce plugin ?

```groovy
buildscript {
    repositories {
    }
    dependencies {
        classpath "io.soffa.tools:soffa-gradle-plugin:<version>"
    }
}

```

Une fois activé, un ensemble de plugins sont disponibles pour le développeur.

### Liste des plugins disponible

#### Général

```groovy
apply plugin: 'soffa.java8' // Configurer le projet en Java8
```

#### Couverture de code

Ce plugin rajoute à votre projet la configuration nécessaire pour faire de la couverture de code
avec [Jacoco](https://www.eclemma.org/jacoco/).

```groovy
apply plugin: 'soffa.coverage.l1' // 10% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l2' // 20% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l3' // 30% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l4' // 40% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l5' // 50% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l6' // 60% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l7' // 70% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l8' // 80% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.l9' // 90% de couverture de code (lignes) attendue
apply plugin: 'soffa.coverage.lX' // 100% de couverture de code. Mode BOSS activé :)
```

#### Lombok

Rajouter [Lombok](https://projectlombok.org) à votre projet.

```groovy
apply plugin: 'soffa.lombok'
```

> Il est important d'activer le plugin au niveau de l'éditeur: *AnnotationProcessing*.

#### Maven

```groovy
apply plugin: 'soffa.maven-library'
```

Ce plugin rajoute la configuration nécessaire au projet pour développer une librairie java et utiliser la
commande `gradle publish` pour transmettre l'artefact à un serveur maven..

