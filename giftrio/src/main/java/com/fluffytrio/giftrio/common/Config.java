package com.fluffytrio.giftrio.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource(value = {"config.properties"})
public class Config {

    @Value("${config.filepath}")
    private String filepath;
    public static String publicFilePath;

    @PostConstruct
    private void initialize() {
        publicFilePath = filepath;
    }

    public static String getFilePath() {
        return publicFilePath;
    }
}
