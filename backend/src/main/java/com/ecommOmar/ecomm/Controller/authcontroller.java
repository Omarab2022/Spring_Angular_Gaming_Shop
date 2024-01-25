package com.ecommOmar.ecomm.Controller;


import com.ecommOmar.ecomm.Entity.User;
import com.ecommOmar.ecomm.Repository.userRepository;
import com.ecommOmar.ecomm.Services.auth.authservice;
import com.ecommOmar.ecomm.Services.jwt.UserDetailsServiceImpl;
import com.ecommOmar.ecomm.Utils.JWTutils;
import com.ecommOmar.ecomm.dto.Authenticationrequest;
import com.ecommOmar.ecomm.dto.SignupRequest;
import com.ecommOmar.ecomm.dto.UserDto;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class authcontroller {


	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailsService;

	private final userRepository userRepository ;

	private final JWTutils jwTutils;

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";

	private final authservice authservice;


	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody Authenticationrequest authenticationrequest
	, HttpServletResponse response) throws IOException, JSONException {

		try{

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationrequest.getUsername() , authenticationrequest.getPassword()
			));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("incorrect username or password");
		}

		final UserDetails userDetails =userDetailsService.loadUserByUsername(authenticationrequest.getUsername());

		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

		final String jwt = jwTutils.generateToken(userDetails.getUsername());

		if (optionalUser.isPresent()) {

			response.getWriter().write(new JSONObject()
					.put("userID",optionalUser.get().getId())
					.put("role" , optionalUser.get().getRole())
					.toString());

			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, " +
					"X-Requested-With, Content-Type, Accept, X-Custom-header");


			response.addHeader(HEADER_STRING ,TOKEN_PREFIX +jwt);
		}
	}


	@PostMapping("/sign_up")
	public ResponseEntity<?> signupUser (@RequestBody SignupRequest signupRequest){

		if (authservice.hasUserWithEmail(signupRequest.getEmail())) {
			return new ResponseEntity<>("user already exist" , HttpStatus.NOT_ACCEPTABLE);
		}

		UserDto userDto = authservice.createUser(signupRequest);

		return new ResponseEntity<>(userDto , HttpStatus.OK);
	}
}
