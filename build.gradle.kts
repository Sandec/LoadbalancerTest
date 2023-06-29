plugins {
    id("org.openjfx.javafxplugin") version "0.0.14"
    `jpro-gradle-plugin`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

javafx {
    version = "17.0.7"
    modules = listOf("javafx.graphics", "javafx.controls", "javafx.fxml", "javafx.media", "javafx.web")
}

application {
    mainClass.set("one.jpro.loadbalancertest.LoadbalancerTestApp")
    mainModule.set("one.jpro.loadbalancertest")
}

dependencies {
    implementation("fr.brouillard.oss:cssfx:11.5.1")
}

jpro {
    // for debugging
    jvmArgs = listOf("-Xmx100m")

    port = 8080
    releasePlatforms = listOf("all")
}