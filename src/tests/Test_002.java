package tests;

import libraries.Log;
import libraries.Reusable;
import main.MainDriver;

import org.testng.annotations.Test;

public class Test_002 extends MainDriver{
	
	public Test_002(){
		System.out.println("I am in constructor of Test_002");
		
	}
	
	@Test
	public void test_002(){
		Log.startTestCase("Test_002");
		String URL = input.get("DT_URL", "Test_002");
		String username = input.get("DT_USERNAME", "Test_002");
		String password = input.get("DT_PASSWORD", "Test_002");
		System.out.println("Test_002 is Running...");
		module.launchApplication(URL);
		module.login(username, password);
		Log.endTestCase("Test_002");
	}
}
