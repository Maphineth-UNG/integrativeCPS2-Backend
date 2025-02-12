	plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.emse"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.eclipse.org/content/repositories/californium-releases/") } // Californium repository

}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

//	implementation("org.eclipse.californium:californium-core:3.9.0")
//	implementation("org.eclipse.californium:californium-element-connector:3.9.0"
//	implementation("org.eclipse.californium:californium-element-connector-tcp:3.9.0") // Corrected dependency name
	implementation("org.eclipse.californium:californium-core:3.9.0") // Core CoAP support
	implementation("org.eclipse.californium:californium-proxy2:3.9.0") // Proxy support
	implementation("org.eclipse.californium:element-connector:3.9.0") // Corrected dependency name

	implementation("org.springframework.boot:spring-boot-starter-mail") // For email notifications
	implementation("org.springframework.boot:spring-boot-starter-web") // Needed for REST API

	implementation("org.springframework.boot:spring-boot-starter-websocket")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql")

	implementation("com.h2database:h2") // libs to use a H2 database

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
