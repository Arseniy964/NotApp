// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    //Ksp
    id("com.google.devtools.ksp") version "2.0.0-1.0.21" apply false

    //Saveargs
    id("androidx.navigation.safeargs.kotlin") version "2.8.5" apply false

    //Firebase
    id("com.google.gms.google-services") version "4.4.2" apply false
}