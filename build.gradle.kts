plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	id("org.graalvm.buildtools.native") version "0.9.26"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.9.10"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}

kotlin {
	jvmToolchain(17)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
