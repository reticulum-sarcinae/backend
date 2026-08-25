plugins {
  java
  idea
}

val lombokVersion: String by project
val junitVersion: String by project
val springBootVersion: String by project

group = "de.reticulum.sarcinae"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

dependencies {
  implementation(project(":backend"))
}

allprojects {
  apply(plugin = "java")
  apply(plugin = "idea")

  configure<org.gradle.plugins.ide.idea.model.IdeaModel> {
    module {
      isDownloadJavadoc = true
      isDownloadSources = true
    }
  }

  tasks.withType<Test>().configureEach {
    useJUnitPlatform()
  }

  repositories {
    mavenLocal()
    mavenCentral()
  }

  dependencies {
    "implementation"("org.projectlombok:lombok:$lombokVersion")
    "annotationProcessor"("org.projectlombok:lombok:$lombokVersion")

    "testRuntimeOnly"("org.junit.platform:junit-platform-launcher:$junitVersion")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
  }

  configurations {
    named("compileOnly") {
      extendsFrom(configurations.getByName("annotationProcessor"))
    }
  }
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}
