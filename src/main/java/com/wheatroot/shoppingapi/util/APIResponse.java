package com.wheatroot.shoppingapi.util;

public class APIResponse {
    private boolean success;
    private String message;
    
    public APIResponse() {
		
	}
    
	public APIResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "APIResponse [success=" + success + ", message=" + message + "]";
	}
}
