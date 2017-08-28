package com.nuntius.webservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("edge-service")
public interface MicroservicesClient {

	@RequestMapping(method = RequestMethod.GET, value = "/api-comments/comments/")
	public PagedResources<Comment> getComments();
	
}
