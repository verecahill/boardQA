package app.util;

public class Result {
	
	private boolean valid;
	private String errorMessage;
	
	private Result(boolean valid, String errorMessage) {
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	public static Result ok() {
		return new Result(true, null);
	}
	
	public static Result fail(String errorMessage) {
		return new Result(false, errorMessage);
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
