
package com.my.teleport.system.agent.service.application;

import com.googlecode.flyway.core.Flyway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@Slf4j
public class AgentFlywayDatabaseUpgradeService {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String database_url;

    private DataSource dataSource;




    @PostConstruct
    public void upgradeAllTenants() throws SQLException {

        this.dataSource = DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(database_url)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        log.info("FLY WAY Configuration :: START :: Connection :: {}",dataSource.getConnection().toString());
        upgradeTenantDB();
    }


    private void upgradeTenantDB() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setInitOnMigrate(true);
        flyway.setOutOfOrder(true);
        flyway.migrate();
    }


}