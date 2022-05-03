package com.automation.WebAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebAutomationUsingSelenium {

	public WebDriver driver;

	@Test
	public void userIsAbleToNavigateShoppingPage() {

		driver.findElement(By.xpath(".//*[text()='Start Shopping »']")).click();
		String expected = "https://jupiter.cloud.planittesting.com/#/shop";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(expected, actual);
		

	}

	@Test
	public void userIsAbleToNavigateContactPage() throws InterruptedException {

		driver.findElement(By.xpath(".//*[text()='Contact']")).click();

		WebElement welcomeYourFeedback = driver.findElement(By.id("header-message"));
		Assert.assertTrue(welcomeYourFeedback.isDisplayed(), "Contact Page is not Displayed");

	}

	@Test
	public void userIsAbleToAddItemInCartAndTotalItemNumberShouldDisplayOnCart() throws InterruptedException {

		driver.findElement(By.xpath(".//*[text()='Shop']")).click();

		String expected = "https://jupiter.cloud.planittesting.com/#/shop";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(expected, actual);
		WebElement cartNumber = driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a/span"));
		int oldAddedElements = Integer.parseInt(cartNumber.getText());
		System.out.println("Before click on Buy link, cart number is :" + oldAddedElements);
		driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div/p/a")).click();
		int newAddedElements = Integer.parseInt(cartNumber.getText());
		System.out.println("After click on Buy link, cart number is :" + newAddedElements);
		Assert.assertTrue(oldAddedElements + 1 == newAddedElements, "Number in cart doesn't matching with expected");

	}

	@Test
	public void userIsAbleToNavigateLoginPage() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"nav-login\"]/ng-login/a")).click();

		WebElement loginPage = driver.findElement(By.xpath("/html/body/div[3]"));
		Assert.assertTrue(loginPage.isDisplayed(), "Login Page is not Displayed");
	}

	@Test
	public void userIsAbleToNavigateCartPage() {

		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/ul[2]/li[4]/a")).click();
		String expected = "https://jupiter.cloud.planittesting.com/#/cart";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void UserisAbleToSeeCheckOutButtonWhenItemIsInCartAndClickOnCartLink() throws InterruptedException {
		// clicking Start Shopping link
		driver.findElement(By.xpath(".//*[text()='Shop']")).click();
		Thread.sleep(2000);
		// clicking Buy link
		driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div/p/a")).click();
		Thread.sleep(2000);
		// clicking Cart link
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/ul[2]/li[4]/a")).click();
		Thread.sleep(2000);
		WebElement checkOut = driver.findElement(By.xpath(".//*[text()='Check Out']"));
		Assert.assertTrue(checkOut.isDisplayed(), "checkOut Page is not Displayed");

	}

	@Test
	public void userIsAbletoNavigateCheckOutPageIfItemIsInCart() throws InterruptedException {

		driver.findElement(By.xpath(".//*[text()='Shop']")).click();
		Thread.sleep(2000);
		// clicking Buy link
		driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div/p/a")).click();
		Thread.sleep(2000);
		// clicking Cart link
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/ul[2]/li[4]/a")).click();
		Thread.sleep(2000);
		WebElement checkOut = driver.findElement(By.xpath(".//*[text()='Check Out']"));
		checkOut.click();
		Thread.sleep(2000);
		String expected = "https://jupiter.cloud.planittesting.com/#/checkout";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void userIsAbletoSubmitOrder() throws InterruptedException {

		driver.findElement(By.xpath(".//*[text()='Shop']")).click();
		Thread.sleep(2000);
		// clicking Buy link
		driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div/p/a")).click();
		Thread.sleep(2000);
		// clicking Cart link
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/ul[2]/li[4]/a")).click();
		Thread.sleep(2000);
		WebElement checkOut = driver.findElement(By.xpath(".//*[text()='Check Out']"));
		// clicking checkOut link
		checkOut.click();
		Thread.sleep(2000);
		driver.findElement(By.id("forename")).sendKeys("Jhon");
		driver.findElement(By.id("email")).sendKeys("Jhon@gmail.com");
		driver.findElement(By.id("address")).sendKeys("1234");

		WebElement cardType = driver.findElement(By.id("cardType"));
		Select cardTypeBox = new Select(cardType);
		cardTypeBox.selectByValue("Visa");
		driver.findElement(By.id("card")).sendKeys("1234 5678 9012");
		Thread.sleep(2000);
		// clicking submit button
		driver.findElement(By.id("checkout-submit-btn")).click();
		Thread.sleep(10000);

		WebElement orderConfirmation = driver.findElement(By.xpath("/html/body/div[2]/div/div"));
		Assert.assertTrue(orderConfirmation.isDisplayed(), "orderConfirmation Page is not Displayed");
	}

	@Test
	public void userIsAbelToSubmitContactInformation() throws InterruptedException {
		driver.findElement(By.xpath(".//*[text()='Contact']")).click();
		driver.findElement(By.id("forename")).sendKeys("Jhon");
		driver.findElement(By.id("email")).sendKeys("Jhon@gmail.com");
		driver.findElement(By.id("message")).sendKeys("Fantastic website for shopping");
		Thread.sleep(3000);
		// Clicking submit button in contact page
		driver.findElement(By.xpath(".//*[text()='Submit']")).click();
		Thread.sleep(10000);
		WebElement contactSubmission = driver.findElement(By.xpath("/html/body/div[2]/div/div"));
		Assert.assertTrue(contactSubmission.isDisplayed(), "contact Submission Page is not Displayed");
	}

	@Test
	public void userIsAbleToEmptyCartByClickingEmptyCartButton() throws InterruptedException {
		driver.findElement(By.xpath(".//*[text()='Shop']")).click();
		Thread.sleep(1000);
		// clicking Buy link
		driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div/p/a")).click();
		Thread.sleep(1000);
		// clicking Cart link
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/ul[2]/li[4]/a")).click();
		Thread.sleep(1000);
		// clicking EmptyCart link
		driver.findElement(By.xpath(".//*[text()='Empty Cart']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[text()='Yes']")).click();
		Thread.sleep(2000);
		WebElement clearCartConfirmation = driver.findElement(By.xpath("/html/body/div[2]/div/div"));
		String expected = "Your cart is empty - there's nothing to see here.";
		String actual = clearCartConfirmation.getText();

		Assert.assertTrue(actual.contains(expected), "message does not matching");

		WebElement cartNumber = driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a/span"));
		int itemInCart = Integer.parseInt(cartNumber.getText());
		Assert.assertTrue(itemInCart == 0, "Item in cart should be Zero");

	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://jupiter.cloud.planittesting.com/#/");
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
