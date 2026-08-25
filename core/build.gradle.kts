val springBootVersion: String by project
val mapstructVersion: String by project

dependencies {
  implementation(project(":domain:api"))
  implementation(project(":domain:usecase"))

  rootProject.project(":domain:port").subprojects.forEach { portProject ->
    implementation(project(portProject.path))
  }

  implementation("org.mapstruct:mapstruct:$mapstructVersion")
  implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")

  annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

  testAnnotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

}
