dependencies {
  implementation(project(":domain:api"))
  implementation(project(":domain:usecase"))

  rootProject.project(":domain:port").subprojects.forEach { portProject ->
    implementation(project(portProject.path))
  }

  implementation(rootProject.libs.mapstruct)
  implementation(rootProject.libs.spring.boot.starter.web)

  annotationProcessor(rootProject.libs.mapstruct.processor)

  testAnnotationProcessor(rootProject.libs.mapstruct.processor)

}
