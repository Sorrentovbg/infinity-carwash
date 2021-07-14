package ru.infinitycarwash.corelib.entities.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {

    private String login;

    private String password;
}
