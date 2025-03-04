package com.example.demo.test.dbunit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;


@Configuration
public class DbUnitConfig {

    @Value("${spring.datasource.username}")
    private String schema;

    @Bean
    public DatabaseConfigBean dbUnitDatabaseConfig() {
        DatabaseConfigBean bean = new DatabaseConfigBean();
        bean.setQualifiedTableNames(true);
        bean.setCaseSensitiveTableNames(true);
        return bean;
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(
                     DataSource dataSource, DatabaseConfigBean dbUnitDatabaseConfig ) {
        DatabaseDataSourceConnectionFactoryBean bean = 
                     new DatabaseDataSourceConnectionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setDatabaseConfig(dbUnitDatabaseConfig);
        bean.setSchema(schema);
        return bean;
    }

}
