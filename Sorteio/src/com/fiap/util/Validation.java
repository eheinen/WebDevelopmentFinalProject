package com.fiap.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public static boolean hasAlreadyShuffle(User user){
		if(user.getFriendId() != null)
			return true;
		return false;
	}
	
	public static boolean hasNotAnotherFriendChosen(List<User> users, User userChosen){
		boolean flag = false;
		for(User user : users){
			if(user.getFriendId() == null)
				flag = true;
			else
				if(user.getFriendId().getId() == userChosen.getId()){
					flag = false;
					break;
				}
				else
					flag = true;
		}
		return flag;
	}
}
