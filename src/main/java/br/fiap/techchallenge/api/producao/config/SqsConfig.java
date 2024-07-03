package br.fiap.techchallenge.api.producao.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SqsConfig {
	
	@Value("${aws.sqs.uri}")
	private String uri;
	
	@Bean
	public SqsAsyncClient sqsAsyncClient() {
		return SqsAsyncClient.builder()
				.endpointOverride(URI.create(uri))
				.region(Region.US_EAST_1)
				.build();
		
	}
	
	
	
}
