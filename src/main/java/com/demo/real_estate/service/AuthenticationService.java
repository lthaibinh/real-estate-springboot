package com.demo.real_estate.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.real_estate.config.JwtService;
import com.demo.real_estate.dto.UserDTO;
import com.demo.real_estate.model.Token;
import com.demo.real_estate.model.TokenType;
import com.demo.real_estate.model.user.Role;
import com.demo.real_estate.model.user.User;
import com.demo.real_estate.repository.RoleRepository;
import com.demo.real_estate.repository.TokenRepository;
import com.demo.real_estate.repository.UserRepository;
import com.demo.real_estate.request.AuthenticationRequest;
import com.demo.real_estate.request.RegisterRequest;
import com.demo.real_estate.response.AuthenticationResponse;
import com.demo.real_estate.utils.mapper.UserMapper;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepo;

	private final UserRepository userRepo;

	public AuthenticationResponse register(RegisterRequest request) {
		Set<Role> roles = new HashSet<Role>();

		for (String role : request.getRole()) {
			roles.add(new Role(role));

		}
		var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.username(request.getUsername()).build();
		user.setRoles(roles);
		System.out.println(roles);
		System.out.println(user);
		var savedUser = repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		
			UserDetails userDetails = userRepo.findByUsername(request.getUsername()).orElse(null);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

			Authentication authenticate = authenticationManager
					.authenticate(authentication);
			
			User user1 = (User) authenticate.getPrincipal();
			
			var user = repository.findByUsername(request.getUsername()).orElseThrow();
			var jwtToken = jwtService.generateToken(user);
			var refreshToken = jwtService.generateRefreshToken(user);
			revokeAllUserTokens(user);
			saveUserToken(user, jwtToken);
			UserDTO userDTO = UserMapper.getInstance().toDTO(user);
			return AuthenticationResponse
						.builder()
						.accessToken(jwtToken)
						.refreshToken(refreshToken)
						.user(userDTO)
						.build();
		
	}

	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false)
				.build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	public void refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws IOException, StreamWriteException, DatabindException, java.io.IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		if (userEmail != null) {
			var user = this.repository.findByUsername(userEmail).orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
						.build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}
}
