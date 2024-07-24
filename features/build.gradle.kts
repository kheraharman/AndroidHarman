plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    id(Plugin.kotlinKapt)
    id(Plugin.hiltAndroid)
}

android {
    namespace = "com.android.features"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    //Modules
    implementation(project(Module.CoreUi))
    implementation(project(Module.Data))

    //Libraries
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.lifecycleRuntimeCompose)
    implementation(AndroidX.lifecycleLivedataKtx)
    implementation(AndroidX.lifecycleViewModelCompose)
    implementation(AndroidX.lifecycleViewModelKtx)

    //Compose
    implementation(AndroidX.activityCompose)
    implementation(platform(AndroidX.composeBom))
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeUiGraphics)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.composeMaterial3)
    implementation(AndroidX.navigationCompose)

    //Hilt
    implementation(HiltAndroid.hiltAndroid)
    kapt(HiltAndroid.hiltAndroidCompiler)
    implementation(HiltAndroid.hiltAndroidTesting)
    implementation(HiltAndroid.hiltNavigationCompose)

    //Testing
    testImplementation(TestingLibs.junit)
    testImplementation(TestingLibs.kotlinCoroutineTest)
    testImplementation(TestingLibs.mockitoCore)
    testImplementation(TestingLibs.mockitoKotlin)
    testImplementation(TestingLibs.archCore)
    androidTestImplementation(platform(AndroidX.composeBom))
    androidTestImplementation(TestingLibs.composeUiTestJunit4)
    debugImplementation(TestingLibs.composeUiTooling)
    debugImplementation(TestingLibs.composeUiTestManifest)
}