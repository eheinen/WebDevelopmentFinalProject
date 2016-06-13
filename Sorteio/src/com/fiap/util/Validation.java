package com.fiap.util;

import java.util.List;

import com.fiap.entity.User;

public class Validation {

	public static boolean containData(String data){
		if(data == null || data.isEmpty()){
			System.out.println("Sth is null or empty");
			return false;
		}
		return true;
	}
	
	public static boolean containData(String... data){
		if(data == null) return false;
		for(String text : data){
			if(!containData(text)){
				return false;
			};
		}
		return true;
	}
	
	public static boolean hasAnotherFriendChosen(List<User> users, User userChosen){
		long qty = users.stream()
			.filter(u -> u.getFriendId().getId() == userChosen.getId())
			.count();
		return qty > 0;
	}
}
