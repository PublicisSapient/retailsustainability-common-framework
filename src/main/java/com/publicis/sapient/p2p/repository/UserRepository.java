package com.publicis.sapient.p2p.repository;

import com.publicis.sapient.p2p.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    
    User findUserByEmail(String username);
}
