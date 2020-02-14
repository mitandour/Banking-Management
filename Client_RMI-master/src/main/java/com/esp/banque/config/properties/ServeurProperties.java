package com.esp.banque.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(ServeurProperties.PREFIX)
public class ServeurProperties {

    public static final String PREFIX = "serveur";

    private String host;
    private Integer port;

    public ServeurProperties() {
    }

    public ServeurProperties(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
