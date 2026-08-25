pluginManagement {
  val springBootVersion: String by settings

  plugins {
    id("org.springframework.boot") version springBootVersion
  }
}

rootProject.name = "sarcina"

include("backend")
include("core")
include("domain:api")
include("domain:usecase")
include("domain:port")
file("domain/port").listFiles()
  ?.filter { it.isDirectory && it.name != "build" }
  ?.forEach { portModule ->
    include("domain:port:${portModule.name}")
}
include("adapter:persistence")
