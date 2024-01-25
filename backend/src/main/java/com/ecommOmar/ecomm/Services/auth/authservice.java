package com.ecommOmar.ecomm.Services.auth;

import com.ecommOmar.ecomm.dto.SignupRequest;
import com.ecommOmar.ecomm.dto.UserDto;

public interface authservice {

	UserDto createUser(SignupRequest signupRequest);

     Boolean hasUserWithEmail(String email) ;
}
