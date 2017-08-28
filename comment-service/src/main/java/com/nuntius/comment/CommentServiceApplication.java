package com.nuntius.comment;

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
public class CommentServiceApplication {

	
	
	public static void main(String[] args) {

		final ConfigurableApplicationContext context = SpringApplication.run(CommentServiceApplication.class, args);
		final CommentRepository repo = context.getBean(CommentRepository.class);
		for(int i = 0 ; i < 100 ; i++){
			Comment comment = new Comment();
			comment.setText("Esto es un comentario y su ID es: " + comment.getId());

			repo.save(comment);
		}
		
		

	}

}
