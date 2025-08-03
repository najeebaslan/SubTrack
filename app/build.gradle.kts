plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "com.najeeb.movies"
  compileSdk = 36

  defaultConfig {
    applicationId = "com.najeeb.movies"
    minSdk = 28
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)



  implementation("com.google.accompanist:accompanist-adaptive:0.26.2-beta")
  implementation(libs.androidx.adaptive.android)
  implementation(libs.androidx.navigation.common.android)
  implementation("androidx.navigation:navigation-compose:2.7.3")
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation("androidx.core:core-splashscreen:1.0.0")
  // For manage StatueBar
  implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha")
  // For Pick Image
  implementation("io.coil-kt:coil-compose:2.5.0")
  implementation (libs.compose.charts)

  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.ui.viewbinding)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}