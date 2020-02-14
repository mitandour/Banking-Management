package com.esp.banque.config.datasource;

import com.esp.banque.config.properties.DatasourceProperties;
import com.esp.banque.config.properties.HibernateProperties;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ahmad on 4/6/17.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.esp.banque.dao")
public class PersistenceConfig {

    private final HibernateProperties hibernateProperties;
    private final DatasourceProperties datasourceProperties;

    @Autowired
    public PersistenceConfig(HibernateProperties hibernateProperties, DatasourceProperties datasourceProperties) {
        this.hibernateProperties = hibernateProperties;
        this.datasourceProperties = datasourceProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.esp.banque.domain");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(datasourceProperties.getDriverClassName()));
        dataSource.setUrl(Preconditions.checkNotNull(datasourceProperties.makeJDBCUrl()));
        dataSource.setUsername(Preconditions.checkNotNull(datasourceProperties.getUsername()));
        dataSource.setPassword(Preconditions.checkNotNull(datasourceProperties.getPassword()));
        if (datasourceProperties.reloadEnabled()) initialize(dataSource);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private final Properties additionalProperties() {
        final Properties hibernateProps = new Properties();
        hibernateProps.setProperty("hibernate.default_schema", datasourceProperties.getSchema());
        hibernateProps.setProperty("hibernate.default_catalog", datasourceProperties.getDatabase());
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getHbm2ddl_auto());
        hibernateProps.setProperty("hibernate.dialect", hibernateProperties.getDialect());
        hibernateProps.setProperty("hibernate.show_sql", String.valueOf(hibernateProperties.getShow_sql()));
        return hibernateProps;
    }

    private void initialize(DataSource dataSource) {
        ClassPathResource dataResource = new ClassPathResource("data.sql");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(dataResource);
        populator.execute(dataSource);
    }
}
