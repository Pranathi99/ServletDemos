package com.demo.mvc.utility;

import java.util.ArrayList;
import java.util.List;

import com.demo.mvc.model.User;

public class UserDataUtil {
	public static List<User> userList()
	{
		List<User>user_list=new ArrayList<User>();
		user_list.add(new User("Alex","Dunphy","alex@example.com","1234"));
		user_list.add(new User("Haley","Dunphy","haley@example.com","123"));
		user_list.add(new User("Luke","Dunphy","luke@example.com","12344"));
		user_list.add(new User("Claire","Dunphy","claire@example.com","12347"));
		user_list.add(new User("Phil","Dunphy","phil@example.com","123465"));
		return user_list;
	}
}
