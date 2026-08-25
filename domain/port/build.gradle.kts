plugins {
  java
}

subprojects {
  dependencies {
    implementation(project(":domain:api"))
  }
}
