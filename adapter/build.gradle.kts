plugins {
  java
}

val mapstructVersion: String by project

subprojects {
  dependencies {
    "implementation"(project(":domain:api"))
    "implementation"("org.mapstruct:mapstruct:$mapstructVersion")
    "annotationProcessor"("org.mapstruct:mapstruct-processor:$mapstructVersion")
  }
}
