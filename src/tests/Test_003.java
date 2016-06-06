package tests;

import libraries.Log;
import main.MainDriver;

import org.testng.annotations.Test;

public class Test_003 extends MainDriver{

	public Test_003(){
		System.out.println("I am in constructor of Test_003");
		
	}
	
	@Test
	public void test_003(){
		
		Log.startTestCase("Test_003");
		String URL = input.get("DT_URL", "Test_003");
		String username = input.get("DT_USERNAME", "Test_003");
		String password = input.get("DT_PASSWORD", "Test_003");
		System.out.println("Test_003 is Running...");
		module.launchApplication(URL);
		module.login(username, password);
		Log.endTestCase("Test_003");
	}
}
