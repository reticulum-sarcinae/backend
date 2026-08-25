val springBootVersion: String by project
val flywayVersion: String by project
val maiadbClientVersion: String by project

dependencies {
  implementation(project(":domain:port:persistence-port"))
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
  implementation("org.springframework.boot:spring-boot-starter-flyway:$springBootVersion")
  implementation("org.flywaydb:flyway-mysql:$flywayVersion")
  implementation("org.mariadb.jdbc:mariadb-java-client:$maiadbClientVersion")
}
