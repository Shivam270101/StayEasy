package com.stayeasy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	private Long user_id;
    private String token;

    private long expiresIn;

//    public String getToken() {
//        return token;
//    }

 // Getters and setters...
}
