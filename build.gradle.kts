import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detekt)
}

// General configuration of Detekt tool
detekt {
    toolVersion = libs.versions.detekt.get()
    config.from(files("config/detekt/detekt.yml"))
    baseline = file("config/detekt/baseline.xml")
    buildUponDefaultConfig = true
}

// Configure detekt reporting and rules
tasks.withType<Detekt>().configureEach {
    parallel = true
    ignoreFailures = false
    setSource(file(projectDir))
    include("**/*.kt")
    exclude("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
    reports {
        xml.required.set(true)
        html.required.set(true)
        md.required.set(true)
        txt.required.set(false)
        sarif.required.set(false)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}
