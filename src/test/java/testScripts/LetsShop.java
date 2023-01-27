package testScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import accelarators.Base;

public class LetsShop extends Base {
	
	@Test
	public static void mainTest() throws InterruptedException {
		log.info("MAIN TEST EXECUTION STARTED");
		String productName = "ZARA COAT 3";
		webDriver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("testk@test.com");
		webDriver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("123Welcome");
		webDriver.findElement(By.xpath("//input[@id='login']")).click();

		List<WebElement> products = webDriver.findElements(By.cssSelector(".mb-3"));
		WebElement ele = products.stream().filter(
				product -> product.findElement(By.cssSelector(".mb-3 b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		ele.findElement(By.cssSelector(".mb-3 .rounded:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.cssSelector(".toast-container"))));

		webDriver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		String expected = webDriver.findElement(By.cssSelector(".cart h3")).getText();

		Assert.assertEquals(productName, expected);

		webDriver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();

		webDriver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");

		webDriver.findElement(By.cssSelector(".ta-results button:last-child")).click();

		Thread.sleep(5000);

		webDriver.findElement(By.cssSelector(".action__submit")).click();

		String thankYou = webDriver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", thankYou);
		Thread.sleep(5000);
		log.info("MAIN TEST EXECUTION COMPLETED");
	}

}
