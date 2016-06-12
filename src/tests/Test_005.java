package tests;

import main.MainDriver;

import org.testng.annotations.Test;

public class Test_005 extends MainDriver{

	public Test_005(){
		System.out.println("I am in constructor of Test_005");
	}
	
	@Test
	public void test_005(){
		try{
		
			String url = input.get("url", "Test_005");
			String username = input.get("username", "Test_005");
			String password = input.get("password", "Test_005");
			
			module.launchApplication(url);
			module.loginOsCommApp(username, password);
			
		}catch(Exception e){
			System.out.println("Exception in Class Test_005");
		}
		
	}
}
