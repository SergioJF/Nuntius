package com.nuntius.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class ClientServiceApplication {

	
	
	public static void main(String[] args) {

		final ConfigurableApplicationContext context = SpringApplication.run(ClientServiceApplication.class, args);
		final ClientRepository repo = context.getBean(ClientRepository.class);
		for(int i = 0 ; i < 100 ; i++){
			Client client = new Client();
			client.setEmail("email_"+i+"@dominio.com");
			client.setName("Nombre "+i);
			client.setLastName("Apellido "+i);


			repo.save(client);
		}
		
		

	}

}
