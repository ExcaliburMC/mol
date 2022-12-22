@file:Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
  }

  pluginManagement {
    repositories {
      gradlePluginPortal()
      mavenCentral()
      maven("https://repo.papermc.io/repository/maven-public/")
    }
  }
}

rootProject.name = "mol"

include("core")
include("bukkit_1_19_R1")
