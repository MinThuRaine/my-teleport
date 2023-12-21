
package com.my.teleport.system.order.service.application;

import com.googlecode.flyway.core.Flyway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Service
@Slf4j
public class OrderFlywayDatabaseUpgradeService {

    private DataSource dataSource;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String database_url;

    @Value("${spring.datasource.driver-class-name}")
    private String db_driver;
    public OrderFlywayDatabaseUpgradeService() {
        log.info("FLY WAY Configuration");
    }

    @PostConstruct
    public void upgradeAllTenants() {

        this.dataSource = DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(database_url)
                .driverClassName(db_driver)
                .build();
        log.info("FLY WAY Configuration :: START ::");
        upgradeTenantDB();
    }


    private void upgradeTenantDB() {
        final Flyway flyway = new Flyway();
        flyway.setOutOfOrder(true);
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }


}