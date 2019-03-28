plugins {
    application
    kotlin("jvm") version "1.3.20"
}

application {
    mainClassName = "main.kotlin.MainKt"
}

dependencies {
    compile(kotlin("stdlib"))
    compile("org.apache.logging.log4j:log4j-slf4j-impl:2.11.2")
    compile("org.apache.logging.log4j:log4j-core:2.11.2")
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

tasks.withType<com.commercehub.gradle.plugin.avro.GenerateAvroJavaTask>().configureEach{
    source("src/main/avro")
    
    setOutputDir(file("src/main/generated/"))
}