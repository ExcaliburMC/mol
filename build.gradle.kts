plugins {
  `java-library`
  `maven-publish`
  id("com.github.johnrengelman.shadow") version("7.1.2")
  id("io.freefair.aggregate-javadoc") version "6.6-rc1"
  //id("nebula.javadoc-jar") version "18.4.0"
}

dependencies {
  implementation(project(":bukkit_1_19_R1", "reobf")) {
    isTransitive = false
  }
  implementation(project(":core"))
}

tasks {
  // Custom task
  create<Copy>("rename-javadoc-dir") {
    dependsOn(aggregateJavadoc)
    from(layout.buildDirectory.dir("docs/aggregateJavadoc"))
    into(layout.buildDirectory.dir("docs/javadoc"))
  }
  java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withJavadocJar()
    withSourcesJar()
  }
  build {
    dependsOn(shadowJar)
  }
  shadowJar {
    archiveClassifier.set("")
  }
  javadoc {
    dependsOn("rename-javadoc-dir")
  }
}
allprojects {
  group = "io.github.exmserver"
  version = "1.0.0-SNAPSHOT"
}
