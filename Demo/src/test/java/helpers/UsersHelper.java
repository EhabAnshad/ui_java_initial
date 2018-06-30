package helpers;


import com.google.gson.Gson;

import configurations.User;
import tests.TestBase;

public class UsersHelper extends User
{
	public static User GetAdminUser()
	{
		Gson gson = new Gson(); 
		String systemAdmin = TestBase.jsonTestData.getStringData("systemAdmin");
		User systemUser = gson.fromJson(systemAdmin, User.class);
		return systemUser;
	}
	
	
	
}
