package org.antrain.app.vo;

import lombok.Data;

@Data
public class RegisterVO {
    private String username;
    private String email;
    private String password;
    private String code;
}
