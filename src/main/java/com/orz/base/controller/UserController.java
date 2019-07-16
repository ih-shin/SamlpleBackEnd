package com.orz.base.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orz.base.domain.user.User;
import com.orz.base.domain.user.UserRepository;
import com.orz.common.exception.user.UserDuplicateEmailException;
import com.orz.common.exception.user.UserInvalidPasswordException;
import com.orz.common.exception.user.UserNotFoundException;
import com.orz.common.exception.user.UserUnauthenticatedException;
import com.orz.common.util.jwt.JwtProvider;
import com.orz.common.util.secure.Sha256;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Sha256 sha256;

	@Autowired
	private JwtProvider jwtProvider;

	
	@PostMapping("/user")
	public ResponseEntity<Object> insertUser(@Valid @RequestBody User paramUser) throws Exception {
		
		User user = userRepository.findByUserEmail(paramUser.getUserEmail());
		
		if(user != null) throw new UserDuplicateEmailException();
		
		String salt = sha256.generateSalt();
		paramUser.setCertYn("N");
		paramUser.setSalt(salt);
		paramUser.setUserPwd(sha256.getEncrypt(paramUser.getUserPwd(), salt));
		
		userRepository.save(paramUser);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login/user")
	public ResponseEntity<Object> login(@Valid @RequestBody User paramUser) throws Exception {
		
		User user = userRepository.findByUserEmail(paramUser.getUserEmail());
		 
		if(user == null) throw new UserNotFoundException();

		if("N".equals(user.getCertYn()) || "".equals(user.getCertYn())) throw new UserUnauthenticatedException();
		
		String password = sha256.getEncrypt(paramUser.getUserPwd(), user.getSalt());

		if(!user.getUserPwd().equals(password)) throw new UserInvalidPasswordException();
		
		paramUser.setToken(jwtProvider.createJwt(user.getUserEmail())) ;
		paramUser.setUserPwd("");
		
		return new ResponseEntity<>(paramUser, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userNo}")
	public ResponseEntity<Object> getUser(@PathVariable long userNo) throws Exception {
		
		Optional<User> user = userRepository.findById(userNo);
		
		if(!user.isPresent()) throw new UserNotFoundException();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
