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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Hotel List"
include(":app")

include(":network")
include(":database")
include(":data")

include(":domain")

include(":features:hotel-list-api")
include(":features:hotel-list-impl")
include(":features:hotel-details-api")
include(":features:hotel-details-impl")

include(":common")
