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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ContactUsStepDefinations {


    public String baseUrl = "http://automationpractice.com/index.php";
    public WebDriver driver;
    public static String email = "anitharao@gmail.com";
    public static String password = "12345";
    public String heading1 = "Customer Service";
    public String heading2 = "Webmaster";

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Given("^User is on home page$")
    public void Iam_on_home_page() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#home-page-tabs")).isDisplayed());
        //Assert.assertEquals(driver.getTitle(), "My Store");
    }

    @And("^User can see Contact Us tab$")
    public void userCanSeeContactUsTab() {
        boolean contactUsButton = driver.findElement(By.cssSelector("#contact-link")).isDisplayed();
        Assert.assertTrue(contactUsButton);
    }

    @When("^User clicks Contact Us tab$")
    public void userClicksContactUs() throws InterruptedException {
        WebElement ContactUsButton = driver.findElement(By.cssSelector("#contact-link"));
        ContactUsButton.click();
    }

    @Then("^User should navigate to Customer Service Page$")
    public void userOnCustomerServicePage() {
        boolean contactUsPageStatus = driver.findElement(By.cssSelector(".navigation_page")).isDisplayed();
        Assert.assertTrue(contactUsPageStatus);
    }

    @And("User can see Send a Message details")
    public void seeSendMessageDetails() {
        boolean seeMessageDetails = driver.findElement(By.cssSelector(".contact-form-box")).isDisplayed();
        Assert.assertTrue(seeMessageDetails);
    }

    @When("^User selects subject heading as (.*)$")
    public void selectSubjectHeading(String option) {
        Select subjectHeading = new Select(driver.findElement(By.xpath("//select[@id=\"id_contact\"]")));
        subjectHeading.selectByVisibleText("Customer service");
    }

    @And("User enters Email as {string} and Order reference as {string}")
    public void userEntersEmailAndOrderReference(String email, String ordref) {
     driver.findElement(By.xpath("//input [@id=\"email\"]")).sendKeys(email);
     driver.findElement(By.cssSelector("#id_order")).sendKeys(ordref);
    }
    @And("User clicks on choose file")
    public void userClicksOnChooseFile() {
        driver.findElement(By.xpath("//div[@id=\"uniform-fileUpload\"]")).click();
    }

    @And("User enters message as {string} in message box")
    public void userEntersMessageInMessageTextBox(String message) {
        driver.findElement(By.xpath("//div/textarea")).sendKeys(message);
    }

    @And("clicks on submit")
    public void clicksOnSubmit() {
        driver.findElement(By.cssSelector("#submitMessage")).click();
    }

    @Then("User should see message sent confirmation")
    public void userShouldSeeMessageSentConfirmation() {
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@id=\"center_column\"]/p"));
        boolean confirmationStatus = confirmationMessage.isDisplayed();
        Assert.assertTrue(confirmationStatus);
        confirmationMessage.getText();
        System.out.println(confirmationMessage);
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }
}