package com.poisonedyouth.SpringBootSample.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validateUser(String userid, String password) {
		return userid.equalsIgnoreCase("matthias") && password.equalsIgnoreCase("passw0rd");
	}
}
