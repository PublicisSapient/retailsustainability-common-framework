package com.publicis.sapient.p2p.repository.Impl;

import com.publicis.sapient.p2p.model.User;
import com.publicis.sapient.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User findUserByEmail(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(username));
        return mongoTemplate.findOne(query,User.class);
    }
}
