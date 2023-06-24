package com.usuarios.usuarios.config;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableJpaRepositories(
        basePackages = {
                // "com.usuarios.usuarios.repositories",
                // "com.usuarios.usuarios.security.repository",
                //"com.usuarios.usuarios.additional.repositories"
        },
        entityManagerFactoryRef = "additionalEntityManagerFactory",
        transactionManagerRef = "additionalTransactionManager"
)
public class AdditionalDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.additional-datasource")
    public DataSourceProperties additionalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource additionalDataSource() {
        return additionalDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean additionalEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("additionalDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.usuarios.usuarios.modelsPesaje")
                .persistenceUnit("additional")
                .build();
    }

    @Bean
    public PlatformTransactionManager additionalTransactionManager(
            @Qualifier("additionalEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
