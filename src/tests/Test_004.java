package tests;

import main.MainDriver;

import org.testng.annotations.Test;

public class Test_004 extends MainDriver{

	public Test_004(){
		System.out.println("I am in constructor of Test_004");
	}
	
	@Test
	public void test_004(){
		try{
		
			String url = input.get("url", "Test_004");
			String username = input.get("username", "Test_004");
			String password = input.get("password", "Test_004");
			
			module.launchApplication(url);
			module.loginOsCommApp(username, password);
			
		}catch(Exception e){
			System.out.println("Exception in Class Test_004");
		}
		
	}
}
