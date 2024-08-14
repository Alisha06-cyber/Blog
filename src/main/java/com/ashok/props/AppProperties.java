package com.ashok.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

//read msg from application.properties
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "plan-api")
public class AppProperties {
    private Map<String, String> messages = new HashMap<>();
}