/**
 * Configures the plugin management repositories.
 *
 * This section defines the repositories that Gradle will use to look for plugins.
 *
 * - `google()`: Adds the Google Maven repository, which hosts Android-specific plugins.
 * - `mavenCentral()`: Adds the Maven Central repository, a widely used repository for Java and Kotlin libraries.
 * - `gradlePluginPortal()`: Adds the Gradle Plugin Portal repository, which hosts Gradle plugins.
 */
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

/**
 * Configures dependency resolution management.
 *
 * This section sets the mode for resolving dependencies and defines the repositories to be used.
 *
 * - `repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)`: Ensures that only the repositories defined here are used, and fails if any project-level repositories are defined.
 * - `google()`: Adds the Google Maven repository for Android dependencies.
 * - `mavenCentral()`: Adds the Maven Central repository for general Java and Kotlin dependencies.
 */
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

/**
 * Sets the name of the root project.
 *
 * This is the name that will be used for the root project in the Gradle build.
 */
rootProject.name = "AndroidHarman"

/**
 * Includes the specified modules in the project.
 *
 * This section lists all the modules that are part of the project.
 *
 */
include(":app")
include(":core_ui")
include(":common")
include(":featureFruits:data")
include(":featureFruits:presentation")
include(":featureFruits:domain")
