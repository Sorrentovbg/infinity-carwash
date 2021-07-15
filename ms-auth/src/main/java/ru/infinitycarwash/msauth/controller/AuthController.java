package ru.infinitycarwash.msauth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.infinitycarwash.corelib.entities.UserInfo;
import ru.infinitycarwash.corelib.entities.dto.AuthRequestDto;
import ru.infinitycarwash.corelib.entities.dto.AuthResponseDto;
import ru.infinitycarwash.corelib.entities.dto.SignUpRequestDto;
import ru.infinitycarwash.corelib.interfaces.TokenService;

import ru.infinitycarwash.corelib.services.RedisService;
import ru.infinitycarwash.msauth.entities.User;
import ru.infinitycarwash.msauth.services.UserService;

@RestController
@RequestMapping("/infinity/auth")
@Api(value = "/Auth", tags = {"Регистрация и авторизация"})
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;


    @PostMapping("/login")
    @ApiOperation(
            value = "Залогинится в сервисе",
            httpMethod = "POST",
            response = AuthResponseDto.class
    )
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getLogin())
                .role(user.getRole().getName())
                .build();
        String token = tokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @PostMapping("/signup")
    @ApiOperation(
            value = "Регистрация в сервисе",
            httpMethod = "POST",
            response = String.class
    )
    public String signUp(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
        return "OK";
    }


    @GetMapping("/logout")
    @ApiOperation(
            value = "Выход в сервисе",
            httpMethod = "GET"
    )
    public void logout(@RequestHeader String authorization){
        UserInfo userInfo = tokenService.parseToken(authorization);
        System.out.println("logout(token) = " + authorization);
        System.out.println("UserInfo " + userInfo.getUserId());
        redisService.putInvalidToken(authorization);
    }

    @GetMapping("/getuser")
    public User getUser(String login){
        return userService.findByLogin(login);
    }
}
