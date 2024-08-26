pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://nexus.surpuissant.io/repository/maven/")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "surpuissant.sdk.kws.demo"
include(":app")
 