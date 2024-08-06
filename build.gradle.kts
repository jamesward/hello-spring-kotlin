import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.spring") version "2.0.10"
    kotlin("plugin.power-assert") version "2.0.10"
    id("org.pkl-lang") version "0.26.3"
}

group = "com.example.demo"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly("org.pkl-lang:pkl-spring:0.16.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

kotlin {
    jvmToolchain(17)

    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

pkl {
    kotlinCodeGenerators {
        register("configClasses") {
            generateSpringBootConfig = true
            generateKdoc = true
            sourceModules = listOf(file("src/main/resources/AppConfig.pkl").toURI())
        }
    }
}

tasks.named("configClasses") {
    doLast {
        outputs.files.forEach { dir ->
            fileTree(dir).forEach { file ->
                val contents = file.readLines()
                val updated = contents.filterNot { it.contains("ConstructorBinding") }
                file.writeText(updated.joinToString("\n"))
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        showExceptions = true
        exceptionFormat = TestExceptionFormat.FULL
        events(TestLogEvent.STARTED, TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
    }
}
