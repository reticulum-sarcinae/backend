import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  java
  id("org.springframework.boot")
}

val springBootVersion: String by project
val springSecurityVersion: String by project
val flywayVersion: String by project
val mapstructVersion: String by project
val testcontainersVersion: String by project
val maiadbClientVersion: String by project

tasks.named<BootJar>("bootJar") {
  archiveFileName.set("app.jar")
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

  implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
  implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
  implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
  implementation("org.mapstruct:mapstruct:$mapstructVersion")

  annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

  testImplementation(project(":adapter:persistence"))
  testImplementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
  testImplementation("org.springframework.security:spring-security-test:$springSecurityVersion")
  testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test:$springBootVersion")

  testImplementation("org.testcontainers:mariadb:$testcontainersVersion")
  testImplementation("org.flywaydb:flyway-core:$flywayVersion")
  testRuntimeOnly("org.mariadb.jdbc:mariadb-java-client:$maiadbClientVersion")
}
