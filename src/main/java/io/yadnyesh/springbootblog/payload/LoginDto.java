package io.yadnyesh.springbootblog.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
