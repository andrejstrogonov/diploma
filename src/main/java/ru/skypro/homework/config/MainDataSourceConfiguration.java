package ru.skypro.homework.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.AvatarAdModel;
import ru.skypro.homework.model.AvatarUserModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
        * Класс, позволяющий организовать взаимодействие с базой данных PostgreSql.
 */
@EnableJpaRepositories(
        entityManagerFactoryRef = "bookingEntityManager",
        transactionManagerRef = "bookingTransactionManager",
        basePackages = "ru.skypro.homework.repository")
@Configuration
public class MainDataSourceConfiguration {
    /**
     * Устанавливаем соединение с базой данных, параметры которой расположены в папке application.properties
     * в строках начинающихся на spring.datasource
     */
    @Primary
    @Bean
   // @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource myDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/diploma");
        dataSource.setUsername("user");
        dataSource.setPassword("123");
        return dataSource;
    }

    /**
     * Производим настройку жизненного цикла нашего myEntityManagerFactory
     */
    @Primary
    @Bean(name = "bookingEntityManager")
    public LocalContainerEntityManagerFactoryBean myEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(myDataSource())
                .packages(UserModel.class)
                .packages(AvatarUserModel.class)
                .packages(AvatarAdModel.class)
                //.packages(AdModel.class)
                .build();
    }
    @Bean(name = "bookingTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("bookingEntityManager") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
    //@Bean(name = "bookingTransactionManager")
    //public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("userEntityManager") EntityManagerFactory entityManagerFactory) {
    //    return new JpaTransactionManager(entityManagerFactory);
   // }

}