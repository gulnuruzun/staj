plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Swagger / SpringDoc UI kütüphanesi
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}