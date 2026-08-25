dependencies {
  implementation(project(":domain:port:persistence-port"))
  implementation(rootProject.libs.spring.boot.starter.data.jpa)
  implementation(rootProject.libs.spring.boot.starter.flyway)
  implementation(rootProject.libs.flyway.mysql)
  implementation(rootProject.libs.mariadb.java.client)
}
