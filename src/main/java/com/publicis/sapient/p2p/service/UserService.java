package com.publicis.sapient.p2p.service;

import com.publicis.sapient.p2p.model.User;
import com.publicis.sapient.p2p.repository.Impl.UserRepositoryImpl;
import com.publicis.sapient.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserService {

    @Autowired
    private UserRepository userRepositoryImpl;
    
    public UserService(UserRepository userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }

    public User getUser(String email){
        User user =  userRepositoryImpl.findUserByEmail(email);
        return user;
    }
}
