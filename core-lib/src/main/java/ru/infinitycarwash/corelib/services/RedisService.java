package ru.infinitycarwash.corelib.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.infinitycarwash.corelib.repositories.RedisRepository;

import java.time.Duration;

@Service
public class RedisService {
    @Autowired
    RedisRepository redisRepository ;

    public void putInvalidToken(String token){
        System.out.println("token = " + token);
        redisRepository.putToken(token, Duration.ofHours(1));
    }

    public boolean checkToken(String authorizationHeader){
        System.out.println("authorizationHeader = " + authorizationHeader);
        boolean existToken = false;
        if(redisRepository.getToken(authorizationHeader) != null){
            existToken = true;
        }
        return existToken;
    }
}
