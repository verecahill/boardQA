package app.util;

import javax.servlet.http.HttpSession;

import app.dto.Account;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionedUser";
	
	public static boolean isLoginUser(HttpSession session) {
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		if (sessionedUser == null) {
			return false;
		}
		return true;
	}
	
	public static Account getUserFromSession(HttpSession session) {
		if (!isLoginUser(session)) {
			return null;			
		}
		
		return (Account)session.getAttribute(USER_SESSION_KEY);
	
	}
}
