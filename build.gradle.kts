plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.1").apply(false)
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
}

buildscript {
    val sqlDelightVersion = "1.5.3"
    val hiltVersion = "2.42"
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
