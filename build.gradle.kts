import org.gradle.plugins.ide.idea.model.IdeaModel

plugins {
  java
  idea
}

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
  apply {
    plugin("java")
    plugin("idea")
  }

  configure<IdeaModel> {
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
    implementation(rootProject.libs.lombok)
    annotationProcessor(rootProject.libs.lombok)

    constraints {
      implementation(rootProject.libs.tomcat.embed.core) {
        because("Override Spring Boot's transitive Tomcat core version.")
      }
    }

    testRuntimeOnly(rootProject.libs.junit.platform.launcher)
    testImplementation(rootProject.libs.spring.boot.starter.test)
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
