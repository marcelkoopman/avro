plugins {
    application
    kotlin("jvm") version "1.3.20"
    java
}

application {
    mainClassName = "main.kotlin.MainKt"
}

dependencies {
    compile(kotlin("stdlib"))
    compile("org.apache.avro:avro:1.8.2")
}

repositories {
    jcenter()
}

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.commercehub.gradle.plugin:gradle-avro-plugin:0.16.0")
    }
}

apply(plugin="com.commercehub.gradle.plugin.avro")

apply(plugin="java")

sourceSets.create("generated") {
    java.setSrcDirs(listOf("src/main/java","src/gen/java").asIterable())
}

tasks.withType<com.commercehub.gradle.plugin.avro.GenerateAvroJavaTask>().configureEach{
    source("src/main/avro")
    setOutputDir(file("src/gen/java"))
}

tasks.named<JavaCompile>("compileJava") {
    source("src/gen/java")
}