package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    static String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {

        openBrowser(baseUrl);

    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {

        // click on the ‘Sign In’ link
        driver.findElement(By.xpath("//a[contains(@href,'/users/sign_in')]")).click();

        // expected message in teh redirected page
        String expectedText = "Welcome Back!";

        // Verify the text ‘Welcome Back!’
        String actualText = driver.findElement(By.xpath("//h2[contains(@class,'page__heading')]")).getText();
        Assert.assertEquals(expectedText,actualText);

    }

    @Test
    public void verifyTheErrorMessage() {

        // redirect to the sign-in page
        userShouldNavigateToLoginPageSuccessfully();

        // find the element for email field and enter in-valid email
        driver.findElement(By.name("user[email]")).sendKeys("ABCD");

        // find the element for password field and enter in-valid password
        driver.findElement(By.name("user[password]")).sendKeys("1234");

        // find the element to submit and click
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();

        // find the element for the error message and store the error message
        String actualError = driver.findElement(By.xpath("//li[@class = 'form-error__list-item']")).getText();
         // expected error message
        String expectedError = "Invalid email or password.";

        // verify expected and actual text
        Assert.assertEquals(expectedError, actualError);
    }

    @After
    public void tearTown () {
        // calling method from BaseTest class to close the browser
        closeBrowser();
    }

}
