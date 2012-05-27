package com.bitwiseor.extensions.config

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
class BatchConfig {
	@Value('${batch.jdbc.driver}')
	private def driverClassName

	@Value('${batch.jdbc.url}')
	private def driverUrl

	@Value('${batch.jdbc.user}')
	private def driverUsername;

	@Value('${batch.jdbc.password}')
	private def driverPassword;

	@Autowired
	@Qualifier("jobRepository")
	def jobRepository;

	@Bean
	def dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.driverClassName = driverClassName;
		dataSource.url = driverUrl;
		dataSource.username = driverUsername;
		dataSource.password = driverPassword;
		return dataSource;
	}

	@Bean
	def jobLauncher() {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.jobRepository = jobRepository;
		return jobLauncher;
	}

	@Bean
	def transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
