package app.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.dto.Account;
import app.dto.Role;
import app.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void saveUser(Account user, String[] roles) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<Role> rolesSet = new HashSet<Role>();
		for(String role:roles) {
			rolesSet.add(new Role(role));
		}
		user.setRoles(rolesSet);
		userRepository.save(user);
	}

	@Override
	public Account findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	

}
