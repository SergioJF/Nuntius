package com.nuntius.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource
public interface CommentRepository extends MongoRepository<Comment,Long>{

	
}
