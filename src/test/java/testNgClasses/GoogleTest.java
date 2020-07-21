package testNgClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class GoogleTest {
    WebDriver driver;
    @BeforeClass
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(/*priority = 1 or enabled=false*/)
    public void googleTitleTest(){
        String actualTitle=driver.getTitle();
        String expectedTitle="Google";
        Assert.assertEquals(actualTitle,expectedTitle,"not equal");
    }
    @Test(/*priority = 2*/)
    public void googleLogoDisplayTest(){
        WebElement logo=driver.findElement(By.id("hplogo"));
        System.out.println("Logo display situation= "+logo.isDisplayed());

    }
    @Test( /*priority = 3*/ dependsOnMethods = "googleLogoDisplayTest")
    public void gmailLinkDisplayTest(){
        WebElement gmailLink=driver.findElement(By.xpath("//a[contains(text(),'Gmail')]"));
        System.out.println("is gmail link enabled (T/F)? = "+gmailLink.isEnabled());
        gmailLink.click();
    }
}
