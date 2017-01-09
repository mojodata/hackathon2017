package com.rbc.rbcone.position.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rbc.rbcone.position.dashboard.news.NewsFeedSources;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(NewsFeedSources.class)
public class PositionDashboardService {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PositionDashboardService.class, args);
	}

}