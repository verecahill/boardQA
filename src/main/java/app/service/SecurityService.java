package app.service;

public interface SecurityService {
	String findLoggedInUserId();
	void autologin(String userId, String password);
}
