package com.jerry.springboot_restful.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@ConfigurationProperties(prefix = "ipconfig")
@PropertySource(value = "classpath:ipconfig.properties")
public class IpConfig {
    @Value("${ipWhiteList}")
    private String ipWhiteList;

    public String getIpWhiteList() {
        return ipWhiteList;
    }
}
