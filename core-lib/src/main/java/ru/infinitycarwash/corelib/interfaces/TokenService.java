package ru.infinitycarwash.corelib.interfaces;

import ru.infinitycarwash.corelib.entities.UserInfo;

public interface TokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);
}
