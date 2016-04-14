package org.sj.oaprj.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class HikariCPConfig {
	
	@Value("${spring.datasource.dataSourceClassName}")
	private String dataSourceClassName;
	
	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String url;
	@Bean
	public DataSource primaryDataSource() {
		HikariConfig hc = new HikariConfig();
		hc.setDataSourceClassName(dataSourceClassName);
		hc.setJdbcUrl(url);
		hc.setUsername(username);
		hc.setPassword(password);
		hc.setAutoCommit(false);
		hc.setMaximumPoolSize(10);
		hc.addDataSourceProperty("databaseName", "sjjt");
		hc.addDataSourceProperty("serverName", "localhost");
		hc.addDataSourceProperty("cachePrepStmts", "true");
		hc.addDataSourceProperty("prepStmtCacheSize", "250");
		hc.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		return new HikariDataSource(hc);
	}
}
