package com.nuntius.webservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MicroserviceCommentsHystrixClient {

	@Autowired
	private MicroservicesClient client;
	
	
	@HystrixCommand(fallbackMethod="getCommentsFallback")
	public PagedResources<Comment> getComments() {
		return client.getComments();
	};
	
	public PagedResources<Comment> getCommentsFallback() {
		List<Comment> content = new ArrayList<>();
		PageMetadata metadata = null;
		Link links = null;
		return new PagedResources<Comment>(content,metadata ,links );
	};

}
