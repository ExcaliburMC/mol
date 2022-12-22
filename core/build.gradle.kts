plugins {
  `java-library`
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
}
// Do not put publishing block because it will be shadowed by shadowJar
