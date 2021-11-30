package com.sanvui.utils;

import com.sanvui.dto.SalaryDTO;
import com.sanvui.model.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * @author: VuiSK
 * @created: 03/11/2021-1:30 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {

        String secretKey = getSecret();
        Datasources datasources = getInformation();
//        encrypt

//        String username1 = AES.encrypt("sa", secretKey);
//        String password1 = AES.encrypt("23102000", secretKey);
//        String url1 = AES.encrypt("jdbc:sqlserver://localhost:1433;database=MYSTORE", secretKey);
//        System.out.println("username1 "+username1);
//        System.out.println("password1 "+password1);
//        System.out.println("url1 "+url1);

//        decrypt
        String username = AES.decrypt(datasources.getUserName(), secretKey);
        String password = AES.decrypt(datasources.getPassword(), secretKey);
        String url = AES.decrypt(datasources.getUrl(), secretKey);

        Configuration configuration = new Configuration();

//        config resources
        ResourceBundle rs = ResourceBundle.getBundle("configdatasource");
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, rs.getString("hibernate.connection.driver_class"));
        properties.put(Environment.URL, url);
        properties.put(Environment.USER, username);
        properties.put(Environment.PASS, password);

//        set utf-8
        properties.put("hibernate.connection.characterEncoding", rs.getString("hibernate.connection.characterEncoding"));
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "hibernate.enable_lazy_load_no_trans");
        properties.put(Environment.HBM2DDL_AUTO, rs.getString("hibernate.hbm2ddl.auto"));
        properties.put(Environment.DIALECT, rs.getString("hibernate.dialect"));
        properties.put(Environment.SHOW_SQL, rs.getString("hibernate.show_sql"));

//        enable second cache
        properties.put(Environment.USE_DIRECT_REFERENCE_CACHE_ENTRIES, rs.getString("hibernate.cache.use_second_level_cache"));
        properties.put(Environment.CACHE_REGION_FACTORY, rs.getString("hibernate.cache.region.factory_class"));

//        enable batch size
        properties.put(Environment.STATEMENT_BATCH_SIZE, rs.getString("hibernate.jdbc.batch_size"));

//        search full text
        properties.put("hibernate.search.default.directory_provider", rs.getString("hibernate.search.default.directory_provider"));
        properties.put("hibernate.search.default.indexBase", rs.getString("hibernate.search.default.indexBase"));

        configuration.setProperties(properties);

//        add annotation
        configuration.addAnnotatedClass(Avatar.class);
        configuration.addAnnotatedClass(Candidates.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Color.class);
        configuration.addAnnotatedClass(ContactStore.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Discount.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Entrytest.class);
        configuration.addAnnotatedClass(Footer.class);
        configuration.addAnnotatedClass(FooterDetail.class);
        configuration.addAnnotatedClass(Images.class);
        configuration.addAnnotatedClass(Interview.class);
        configuration.addAnnotatedClass(JobDescription.class);
        configuration.addAnnotatedClass(JobRequirement.class);
        configuration.addAnnotatedClass(Jobs.class);
        configuration.addAnnotatedClass(Manufacturer.class);
        configuration.addAnnotatedClass(Menu.class);
        configuration.addAnnotatedClass(News.class);
        configuration.addAnnotatedClass(OrderDetails.class);
        configuration.addAnnotatedClass(Orders.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(ProductDetail.class);
        configuration.addAnnotatedClass(Products.class);
        configuration.addAnnotatedClass(Rates.class);
        configuration.addAnnotatedClass(Salary.class);
        configuration.addAnnotatedClass(Sales.class);
        configuration.addAnnotatedClass(SalaryDTO.class);


        ServiceRegistry registry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(registry);
    }

    /*
     * Open session
     * */
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    /*
     * Closer session Factory
     * */
    public void closerSession() {
        sessionFactory.close();
    }


    /*
     * Get information datasource
     * parameter: No
     * request: Object Datasource
     * */
    public static Datasources getInformation() {

        Datasources datasources = new Datasources();

//        get information connection
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {

            inputStream = new FileInputStream("src/main/resources/configdatasource.properties");

//            load properties from file
            properties.load(inputStream);

//            get properties by name
            datasources.setUserName(properties.getProperty("hibernate.connection.username"));
            datasources.setPassword(properties.getProperty("hibernate.connection.password"));
            datasources.setUrl(properties.getProperty("hibernate.connection.url"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close objects
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return datasources;

    }

    /*
     * get secret by file secretKey.properties
     * if false return null
     * return String key
     * */
    public static String getSecret() {

        Properties properties = new Properties();
        InputStream inputStream = null;

        String secretKey = null;

        try {

            inputStream = new FileInputStream("src/main/resources/secretKey.properties");

//            load properties from file
            properties.load(inputStream);

//            get properties by name
            secretKey = properties.getProperty("key");

        } catch (IOException e) {
            System.out.println("Get secretKey fail!");
            return null;
        } finally {
            // close objects
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return secretKey;
    }
}