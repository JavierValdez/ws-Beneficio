package com.usuarios.usuarios.config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.usuarios.usuarios.repositories",
                "com.usuarios.usuarios.repositoriesAditional"
        },
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)

public class DatabaseConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.additional-datasource")
    public DataSource additionalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mainDataSource())
                .packages("com.usuarios.usuarios.models")
                .persistenceUnit("main")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean additionalEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(additionalDataSource())
                .packages("com.usuarios.usuarios.modelsAditional")
                .persistenceUnit("additional")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

    @Bean
    public PlatformTransactionManager additionalTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(additionalEntityManagerFactory(builder).getObject());
    }
}
