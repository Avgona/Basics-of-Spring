package com.spring.basics.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan("com.spring.basics")
public class SpringHibernateConfigs implements WebMvcConfigurer {

    //path to jsp files
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    // setup of Autowire SessionFactory for DAOImpl
    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        // create SessionFactory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(myDataSource());
        sessionFactory.setPackagesToScan("com.spring.basics.entity");
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    // properties of database
    @Bean
    public DataSource myDataSource() {

        // create connection pool
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            myDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // set database connection props
        myDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/basics?useSSL=false&serverTimezone=UTC");
        myDataSource.setUser("hbstudent");
        myDataSource.setPassword("hbstudent");


        // set connection pool props
        myDataSource.setInitialPoolSize(Integer.parseInt("5"));
        myDataSource.setMinPoolSize(Integer.parseInt("5"));
        myDataSource.setMaxPoolSize(Integer.parseInt("20"));
        myDataSource.setMaxIdleTime(Integer.parseInt("3000"));

        return myDataSource;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.show_sql", "true");

        return props;
    }


    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

}
