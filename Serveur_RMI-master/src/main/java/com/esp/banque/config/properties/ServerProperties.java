package com.esp.banque.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(ServerProperties.PREFIX)
public class ServerProperties {

    public static final String PREFIX = "banque.application.serveur";

    private Integer port;

    public ServerProperties() {
    }

    public ServerProperties(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
