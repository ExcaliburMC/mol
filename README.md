# mol
mol is library for Minecraft plugin.  
mol library was created for various plugins on the EXM Server. But you can also use this library for your plugin if you want.

## How to use
mol is hosted on [JitPack](https://jitpack.io/#exmsvr/mol). First add these line to your build script.
```groovy
// build.gradle
repositories {
  //...
  mavenCentral()
  maven { url = "https://jitpack.io" }
}
```
```kotlin
// build.gradle.kts
repositories {
  //...
  mavenCentral()
  maven("https://jitpack.io")
}
```
Then add mol as dependency.
```groovy
// build.gradle
dependencies {
  implementation "com.github.exmsvr:mol:1.0.0-SNAPSHOT"
}
```
```kotlin
// build.gradle.kts
dependencies {
  implementation("com.github.exmsvr:mol:1.0.0-SNAPSHOT")
}
```
