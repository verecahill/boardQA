package app.service;

import app.dto.Account;

public interface AccountService {
	void saveUser(Account user, String[] roles);
	Account findByUserId(String userId);
}
