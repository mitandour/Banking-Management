package com.esp.banque.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ahmad on 4/6/17.
 */

@Component
@ConfigurationProperties(DatasourceProperties.PREFIX)
public class DatasourceProperties {

    public static final String PREFIX = "banque.application.datasource";

    private String source;
    private String host;
    private String port;
    private String username;
    private String password;
    private String driverClassName;
    private Boolean reload = true;
    private String schema = "";
    private String database = "";

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean reloadEnabled() {
        return reload;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }

    public Boolean getReload() {
        return reload;
    }

    public void setReload(Boolean reload) {
        this.reload = reload;
    }

    public String makeJDBCUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append("jdbc:")
                .append(source)
                .append("://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(database)
                .append("?")
                .append("currentSchema=")
                .append(schema)
                .toString();
    }
}
