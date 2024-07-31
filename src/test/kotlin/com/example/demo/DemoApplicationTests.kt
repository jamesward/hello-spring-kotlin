package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ConfigurationPropertiesScan
class DemoApplicationTests {

    @Test
    fun basic(@Autowired appConfig: AppConfig) {
        assert(appConfig.server.endpoints.size == 1)
    }

}
