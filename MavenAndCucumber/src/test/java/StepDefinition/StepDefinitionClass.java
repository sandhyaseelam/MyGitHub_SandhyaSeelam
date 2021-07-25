package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class StepDefinitionClass {

	 WebDriver driver;

	@Given("Home page is displayed and Sign up link is enable")
	public void verifyIfHomePageIsDisplayedAndSignUpLinkIsEnabled() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\seleniumDrivers\\chromedriver.exe");

	   driver = new ChromeDriver();

		driver.get("http://elearningm1.upskills.in/");

		Thread.sleep(2000);
		
		driver.manage().window().maximize();
		
		String homePageText = driver.findElement(By.xpath("//*[@class='homepage active']")).getText();

		Assert.assertEquals("HomePage is displayed", "Homepage", homePageText.trim());
		
		WebElement signUpLink = driver.findElement(By.xpath("//*[@class='nav nav-pills nav-stacked']//li/a"));

		String signUpText = signUpLink.getText();
		
		System.out.println(signUpText);

		Assert.assertEquals("Sign up link is displayed", "Sign up!", signUpText.trim());
	}

	@When("Click on Sign up")
	public void clickOnSignupLink() {

		WebElement signUp = driver.findElement(By.xpath("//*[@href='http://elearningm1.upskills.in/main/auth/inscription.php']"));
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].click();", signUp);
		
	}

	@When("Enter user details in the registration form")
	public void enterUserDetailsInRegistrationForm() {
		
		WebElement firstName = driver.findElement(By.id("registration_firstname"));

		WebElement lastName = driver.findElement(By.id("registration_lastname"));

		WebElement email = driver.findElement(By.id("registration_email"));

		WebElement userName = driver.findElement(By.id("username"));

		WebElement password = driver.findElement(By.id("pass1"));

		WebElement confirmPassword = driver.findElement(By.id("pass2"));

		firstName.sendKeys("Sandhya");

		lastName.sendKeys("Seelam" + Math.random());

		email.sendKeys("seelam@yopmail.com");

		userName.sendKeys("Sandhya" + Math.random());

		password.sendKeys("P@ssw0rd");

		confirmPassword.sendKeys("P@ssw0rd");

	}

	@When("click on Register button")
	public void clickOnRegisterButton() {

		driver.findElement(By.name("submit")).click();
	}

	@Then("User is registered sucessfully")
	public void verifySuccessMessageAndOtherDetailsAfterRegistration() {

		String successMessage = driver.findElement(By.xpath("//*[@class='col-xs-12 col-md-12']")).getText();

		Assert.assertTrue(successMessage.contains("Your personal settings have been registered."));
		
		WebElement myAccountImage= driver.findElement(By.xpath("//*[@class='img-circle']"));
		
		myAccountImage.click();

		String actualEmail = driver.findElement(By.xpath("//*[@class='text-center']/p")).getText();

		Assert.assertEquals("seelam@yopmail.com", actualEmail.trim());

	}

	@Then("User can access Inbox")
	public void accessInboxOnClick() {

		driver.findElement(By.xpath("//a[@title='Inbox']")).click();

		String inboxDetails = driver.findElement(By.xpath("//*[@class='messages-icon active']")).getText();

		Assert.assertTrue(inboxDetails.contains("Messages"));
	}

	@Then("Send a new mail")
	public void composeAndSendANewMailFromTheRegisteredAccount() throws InterruptedException {

		driver.findElement(By.xpath("//*[@title='Compose message']")).click();

		WebElement receipientEmailBox = driver.findElement(By.xpath("//*[@for='compose_message_users']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", receipientEmailBox);

		driver.findElement(By.xpath("//*[@class='select2-search__field']")).sendKeys("seelam@yopmail.com");
		
		WebElement receipientName= driver.findElement(By.xpath("//*[@class='select2-results']/ul/li[1]"));
		
		Actions action= new Actions(driver);
		
		action.moveToElement(receipientName).click().build();
		
		action.perform();
		
		Thread.sleep(2000);

		driver.findElement(By.id("compose_message_title")).sendKeys("Automated Message");
		
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id='compose_message_compose']")).click();
		
		Thread.sleep(2000);
		
		System.out.println("The email is sent successfully");

		driver.close();
	}

}
