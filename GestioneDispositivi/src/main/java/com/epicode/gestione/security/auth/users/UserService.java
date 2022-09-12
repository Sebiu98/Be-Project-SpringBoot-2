package com.epicode.gestione.security.auth.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public List<UserResponse> getAllUsersBasicInformations() {
		return userRepository.findAll().stream()
				.map(user -> UserResponse.builder().userName(user.getUsername())
						.role(user.getRoles().stream().findFirst().get().getRoleName().name()).build())
				.collect(Collectors.toList());
	}

	public UserResponse getUserBasicInformations(String userName) {
		User user = userRepository.findByUsername(userName).get();

		System.out.println(user);

		return UserResponse.builder().userName(userName)
				.role(user.getRoles().stream().findFirst().get().getRoleName().name()).build();

	}

}
