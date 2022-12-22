plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version("1.3.8")
}

dependencies {
  compileOnly(project(":core"))
  paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.19.2-R0.1-SNAPSHOT")
}
