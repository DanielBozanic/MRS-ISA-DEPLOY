package com.MRSISA2021_T15.dto;

public class ChangePassword {
	
	private String oldPassword;
	private String password;
	private String confirmPassword;
	
	public ChangePassword() {
		//hey this is empty because its a deafult constructor duh.....
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
