package libraries;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;


public class Keywords {

	WebDriver webDriver;
	ExcelUtility excelUtils;
	
	public Keywords(WebDriver driver){
		
		this.webDriver = driver;
	}
	
	public void click(String locator){
		 
		try{
			waitForElementPresent(locator, 10);
			if(getWebLocator(ExcelUtility.getLocator(locator)) != null){
				getWebLocator(ExcelUtility.getLocator(locator)).click();
				Log.info("Successfully clicked on : "+locator);
			}else{
				System.out.println("Element not found!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setValue(String locator, String value){
		
		try{
			waitForElementPresent(locator, 10);
			if(getWebLocator(ExcelUtility.getLocator(locator)) != null){
				getWebLocator(ExcelUtility.getLocator(locator)).sendKeys(value);
				Log.info("Successfully value entered in : "+locator);
			}else{
				System.out.println("Element not found!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void waitForElementPresent(String element, int time){
		
		try{
			
			final String webelement = element;
			//new WebDriverWait(webDriver, time).until(ExpectedConditions.visibilityOf(getWebLocator(ExcelUtility.getLocator(element))));
			
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
			
			wait.pollingEvery(500, TimeUnit.MILLISECONDS);
			wait.withTimeout(time, TimeUnit.SECONDS);
			wait.ignoring(NoSuchElementException.class);
			
			Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>(){
				public WebElement apply(WebDriver driver){
					//System.out.println("Waiting for Element : "+webelement);
					Log.info("Waiting for Element : "+webelement);
					WebElement webElement = getWebLocator(ExcelUtility.getLocator(webelement));
					if(webElement != null){
						//System.out.println("Element Found : "+webelement);
						Log.info("Element Found : "+webelement);
					}
					return webElement;
				}
			};
			
			wait.until(function);
			
		}catch(Exception e){
			System.out.println("Exception in waitForElementPresent");
		}
	}
	
	public WebElement getWebLocator(String locator){
		
		WebElement element = null;
		try {
			if (locator.startsWith("//")) {
				element = webDriver.findElement(By.xpath(locator));
			} else {
				try {
					element = webDriver.findElement(By.id(locator));
				} catch (Exception e) {
					try {
						element = webDriver.findElement(By.name(locator));
					} catch (Exception e1) {
						try {
							element = webDriver.findElement(By
									.className(locator));
						} catch (Exception e2) {
							try {
								element = webDriver.findElement(By
										.cssSelector(locator));
							} catch (Exception e3) {
								try {
									element = webDriver.findElement(By
											.linkText(locator));
								} catch (Exception e4) {
									try {
										element = webDriver
												.findElement(By
														.partialLinkText(locator));
									} catch (Exception e5) {
										System.out.println("Unable to get Web Element");
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured while getting Web Element");
		}
		return element;
	}
}
