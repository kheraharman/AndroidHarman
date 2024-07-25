object Versions {
    const val kotlin = "1.9.0"
    const val kotlinCore = "1.13.1"
    const val composeBom = "2023.08.00"
    const val compose = "1.9.0"
    const val lifecycle = "2.8.3"
    const val navigationCompose = "2.7.7"
    const val jUnit = "4.13.2"
    const val jUnitExt = "1.2.1"
    const val espressoCore = "3.6.1"
    const val hilt = "2.51.1"
    const val hiltNavigationCompose = "1.2.0"
    const val retrofit = "2.11.0"
    const val okhttp = "4.12.0"
    const val mockitoCore = "5.10.0"
    const val mockitoKotlin = "4.0.0"
    const val kotlinCoroutineTest = "1.6.0"
    const val archCore = "2.2.0"
    const val kotlinCompile = "1.5.1"
    // Add more versions here
}

object Android {
    const val compileSdk = 34
    const val minSdk = 26
    const val targetSdk = 34
    const val versionCode = 1
    const val versionName = "1.0"
}

object AndroidX {
    const val coreKtx = "androidx.core:core-ktx:${Versions.kotlinCore}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"

    const val activityCompose = "androidx.activity:activity-compose:${Versions.compose}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"

    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
}

object TestingLibs {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"

    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val kotlinCoroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutineTest}"
}

object HiltAndroid {
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
}

object RetrofitLibs {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
}

object Detekt {
    const val version = "1.23.0"
    const val plugin = "io.gitlab.arturbosch.detekt"
    const val detekt = "$plugin:detekt-formatting:$version"
}

