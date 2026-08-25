plugins {
  java
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

subprojects {
  dependencies {
    implementation(project(":domain:api"))
    implementation(rootProject.libs.mapstruct)
    annotationProcessor(rootProject.libs.mapstruct.processor)
  }
}
