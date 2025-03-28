package com.github.syndexmx.caloryintaketracker.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Calorie Intake Calculator API",
                description = "API системы контроля потребляемых калорий",
                version = "1.0.0",
                contact = @Contact(
                        name = "Shapovalov Maxim",
                        url = "https://github.com/syndexmx"
                )
        )
)
public class OpenApiConfig {
}
