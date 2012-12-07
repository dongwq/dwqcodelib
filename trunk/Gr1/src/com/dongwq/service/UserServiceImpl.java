package com.dongwq.service;


public class UserServiceImpl implements UserService {

	@Override
	public String sayHello(String str) {
		return "dwq a" + str;
	}

	
}
