buildscript {

    val kotlinVersion = "1.4.0"

    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.2.0-alpha14")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}
group = "com.rob.lights"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}
