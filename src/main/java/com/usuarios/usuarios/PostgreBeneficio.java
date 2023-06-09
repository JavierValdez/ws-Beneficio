/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "beneficioEntityManagerFactory", transactionManagerRef = "beneficioTransactionManager",
        basePackages = {"com.usuarios.usuarios.repositories"})
public class PostgreBeneficio {
    @Autowired
    private Environment env;

    @Bean(name = "beneficioDataSource")
    public DataSource beneficioDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("beneficio.datasource.url"));
        dataSource.setUsername(env.getProperty("beneficio.datasource.username"));
        dataSource.setPassword(env.getProperty("beneficio.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("beneficio.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean(name = "beneficioEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(beneficioDataSource());
        factoryBean.setPackagesToScan("com.usuarios.usuarios.models");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        factoryBean.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("beneficio.jpa.hibernate.ddl-auto"));
        //properties.put("hibernate.show-sql", env.getProperty("beneficio.jpa.show-sql"));
        properties.put("hibernate.dialect", env.getProperty("beneficio.jpa.database-platform"));
        return factoryBean;
    }

    @Primary
    @Bean(name = "beneficioTransactionManager")
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
}
