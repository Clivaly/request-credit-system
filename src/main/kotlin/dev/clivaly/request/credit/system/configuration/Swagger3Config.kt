package dev.clivaly.request.credit.system.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {

    @Bean
    fun publicApi(): GroupedOpenApi? {
        return  GroupedOpenApi.builder()
            .group("springrequestcreditsystem-public")
            .displayName("Request Credit System")
            .pathsToMatch("/api/customers/**", "/api/credits/**")
            .build()
    }
}