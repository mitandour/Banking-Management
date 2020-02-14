package com.esp.banque.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ahmad on 4/6/17.
 */

@Component
@ConfigurationProperties(HibernateProperties.PREFIX)
public class HibernateProperties {

    public static final String PREFIX = "banque.application.hibernate";

    private String dialect;
    private boolean show_sql;
    private String hbm2ddl_auto;

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public boolean getShow_sql() {
        return show_sql;
    }

    public void setShow_sql(boolean show_sql) {
        this.show_sql = show_sql;
    }

    public String getHbm2ddl_auto() {
        return hbm2ddl_auto;
    }

    public void setHbm2ddl_auto(String hbm2ddl_auto) {
        this.hbm2ddl_auto = hbm2ddl_auto;
    }
}


