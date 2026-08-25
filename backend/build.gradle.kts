
plugins {
  java
  libs.plugins.spring.boot
}

dependencies {
  implementation(project(":domain:api"))
  implementation(project(":domain:usecase"))

  runtimeOnly(project(":core"))
  rootProject.project(":domain:port").subprojects.forEach { portProject ->
    runtimeOnly(project(portProject.path))
  }
  rootProject.project(":adapter").subprojects.forEach { adapterProject ->
    runtimeOnly(project(adapterProject.path))
  }

  implementation(rootProject.libs.spring.boot.starter.web)
  implementation(rootProject.libs.spring.boot.starter.security)
  implementation(rootProject.libs.spring.boot.starter.actuator)
  implementation(rootProject.libs.mapstruct)

  annotationProcessor(rootProject.libs.mapstruct.processor)

  testImplementation(project(":adapter:persistence"))
  testImplementation(rootProject.libs.spring.boot.starter.data.jpa)
  testImplementation(rootProject.libs.spring.security.test)
  testImplementation(rootProject.libs.spring.boot.starter.webmvc.test)

  testImplementation(rootProject.libs.testcontainers.mariadb)
  testImplementation(rootProject.libs.flyway.core)
  testRuntimeOnly(rootProject.libs.mariadb.java.client)
}
