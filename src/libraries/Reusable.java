package libraries;

import main.MainDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Reusable extends MainDriver{

	WebDriver webDriver;
	ExcelUtility excelUtils;
	
	public Reusable(WebDriver driver){
		webDriver = driver;
	}
	
	public void launchApplication(String URL){
		
		try{
			webDriver.get(URL);
			Log.info("Url is launched successfully");
		}catch(Exception e){
			System.out.println("Error while launching application");
			//e.printStackTrace();
		}
	}
	
	public void login(String username, String password){
		try{
			actions.click("GWCHomePage.SignIn");
			actions.setValue("GWCHomePage.Email", username);
			actions.setValue("GWCHomePage.Password", password);
			
			actions.click("GWCHomePage.LoginButton");
			
			Assert.assertTrue(webDriver.getTitle().equalsIgnoreCase("McDonalds"));
			Log.info("Successfully logged in");
			
		}catch(Exception e){
			System.out.println("Error while login into application");
			e.printStackTrace();
		}
	}
	
	public void loginOsCommApp(String username, String password){
		try{
			actions.click("OsCommHomePage.LogIn");
			actions.waitForElementPresent("OsCommLogInPage.EmailTxtBox", 20);
			actions.setValue("OsCommLogInPage.EmailTxtBox", username);
			actions.waitForElementPresent("OsCommLogInPage.PasswordTxtBox", 20);
			actions.setValue("OsCommLogInPage.PasswordTxtBox", password);
			
			actions.click("OsCommLogInPage.SignInButton");
			
			Thread.sleep(3000);
			if(webDriver.findElement(By.xpath("//*[@class='ui-button-text'][contains(text(),'Log Off')]")).isDisplayed()){
				System.out.println("Passed");
				Assert.assertEquals(2, 2);
			}else{
				System.out.println("Failed");
				Assert.assertEquals(2, 4);
			}
			
			
		}catch(Exception e){
			System.out.println("Error while login into application");
			//e.printStackTrace();
		}
	}
}
