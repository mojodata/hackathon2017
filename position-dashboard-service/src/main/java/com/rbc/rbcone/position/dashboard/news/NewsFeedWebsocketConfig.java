package com.rbc.rbcone.position.dashboard.news;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@EnableScheduling
public class NewsFeedWebsocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(newsFeedHandler(), "/newsfeed").setAllowedOrigins("*");
	}
	
	@Bean
	public NewsFeedHandler newsFeedHandler() {
		return new NewsFeedHandler();
	}

	@Bean
	public RestOperations rssFeedFetcher() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}
}