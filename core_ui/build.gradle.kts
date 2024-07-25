plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    id(Detekt.plugin) version Detekt.version
}

android {
    namespace = "com.android.core_ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompile
    }
    /**
     * Configures Detekt for static code analysis.
     * - `toolVersion`: Specifies the version of Detekt to use.
     * - `config`: Sets the path to the Detekt configuration file.
     * - `buildUponDefaultConfig`: Indicates whether to build upon the default Detekt configuration.
     */
    detekt {
        toolVersion = Detekt.version
        config.setFrom(file("../config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
    }
}

dependencies {

    val composeBom = platform(AndroidX.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    //Compose
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial3)
    implementation(AndroidX.activityCompose)
    implementation(AndroidX.lifecycleViewModelCompose)
    implementation(AndroidX.navigationCompose)
    implementation(HiltAndroid.hiltNavigationCompose)
    implementation(AndroidX.composeUiToolingPreview)
    debugImplementation(TestingLibs.composeUiTooling)
    androidTestImplementation(TestingLibs.composeUiTestJunit4)
    debugImplementation(TestingLibs.composeUiTestManifest)

}