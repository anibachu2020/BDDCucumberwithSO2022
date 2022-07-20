package AutomationLibrary;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    public String baseUrl = "http://automationpractice.com/index.php";
    public WebDriver driver;
    public static String email = "anitharao@gmail.com";
    public static String password ="12345";

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Given("^I am on home page$")
    public void Iam_on_home_page() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#home-page-tabs")).isDisplayed());
        //Assert.assertEquals(driver.getTitle(), "My Store");
    }

    @When("^I click SignIn button$")
    public void clickSignIn() throws InterruptedException {
        Thread.sleep(30000);
        WebElement signinLink = driver.findElement(By.cssSelector(".login"));
        signinLink.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Then("^I should navigate to authentication page$")
    public void isUserOnAuthenticationPage() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#authentication")).isDisplayed());
    }

    @And("^I should see Login Panel$")
    public void userShouldseeLoginPanel() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#login_form")).isDisplayed());
    }


    @When("I enter username as {string} and Password as {string}")
    public void enterValidCredentials(String email1, String pwd1) {
        driver.findElement(By.cssSelector("#email")).sendKeys(email1);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(pwd1);
    }

    @And("^click Login button$")
    public void clickLogin() {
        driver.findElement(By.cssSelector("#SubmitLogin")).click();
    }

    @Then("^I should navigate to My Account page$")
    public void userIsOnMyAccountage() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".navigation_page")).isDisplayed());
       // String myAccount = driver.findElement(By.className("page-heading")).getText();
      //  Assert.assertEquals(myAccount,"MY ACCOUNT");
    }

    @When("^I enter username as (.*) and Password as (.*)$")
    public void enterInvalidLogindetails(String uname,String Password)
    {
        driver.findElement(By.cssSelector("#email")).sendKeys(uname);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(Password);
    }

    @Then("^I should see error message as (.*)$")
    public void seeValidationMessage(String error) {
       String errormessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
        boolean validationMessage = driver.findElement(By.cssSelector(".alert.alert-danger")).isDisplayed();
        Assert.assertTrue(validationMessage);
       Assert.assertEquals(errormessage,error);
    }



}
