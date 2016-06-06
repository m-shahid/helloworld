package tests;

import main.MainDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_001 extends MainDriver{

	
	
	public Test_001(){
		System.out.println("I am in constructor of Test_001");
	}
	
	@Test
	public void test_001(){
		
		/*String URL = input.get("DT_URL", "Test_001");
		String username = input.get("DT_USERNAME", "Test_001");
		String password = input.get("DT_PASSWORD", "Test_001");
		module.launchApplication(URL);
		module.login(username, password);*/
		
		driver.get(input.get("DT_URL", "Test_001"));
		
		actions.click("GWCHomePage.SignIn");
		
		actions.setValue("GWCHomePage.Email", input.get("DT_USERNAME", "Test_001"));
		actions.setValue("GWCHomePage.Password", input.get("DT_PASSWORD", "Test_001"));
		
		actions.click("GWCHomePage.LoginButton");
		
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("McDonalds"));
		
		
	}
}
