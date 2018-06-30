package helpers;

import configurations.User;
import utilities.KeysGenerators;

public class GenerateUser extends User
{
	public enum UserType
	{
		NormalUser,
		Admin, 
		Visitor
	}
	public GenerateUser(UserType userType, boolean canLogin)
	{

		if (userType == UserType.NormalUser)
		{
			setFirstName(KeysGenerators.getRadomText());
			setMidddleName(KeysGenerators.getRadomText());
			setLastName(KeysGenerators.getRadomText());
		}
		setUsername(KeysGenerators.getRandomEmail());
		setPassword(KeysGenerators.getRadomText());
		setCanLogin(canLogin);
	}

}
