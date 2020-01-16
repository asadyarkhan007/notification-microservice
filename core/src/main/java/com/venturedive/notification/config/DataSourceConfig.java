package com.venturedive.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @PostConstruct
    private void postConstruct() {

    }
    @Bean
    public DataSource getDataSource() {


        Properties mainProperties = new Properties();
        FileInputStream file;
        String path = "./dbconfig.properties";

        try {
            file = new FileInputStream(path);
            mainProperties.load(file);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(mainProperties.getProperty("db.driver"));
        dataSourceBuilder.url(mainProperties.getProperty("db.url"));
        dataSourceBuilder.username(mainProperties.getProperty("db.username"));
        dataSourceBuilder.password( mainProperties.getProperty("db.password"));
        return dataSourceBuilder.build();
    }
}
