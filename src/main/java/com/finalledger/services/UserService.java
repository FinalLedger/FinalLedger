package com.finalledger.services;

import com.finalledger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    public void updateAuthenticationType(String username, String oauth2ClientName) {

        Resource.AuthenticationType authType = Resource.AuthenticationType.valueOf(oauth2ClientName.toUpperCase());
        repo.updateAuthenticationType(username, authType);
    }
}
