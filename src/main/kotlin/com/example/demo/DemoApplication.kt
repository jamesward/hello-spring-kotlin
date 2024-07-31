package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.*

@SpringBootApplication
@ConfigurationPropertiesScan
class DemoApplication {
    @Bean
    fun http(appConfig: AppConfig) = coRouter {
        GET("/") {
            ServerResponse.ok().bodyValueAndAwait(appConfig.server.endpoints)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
