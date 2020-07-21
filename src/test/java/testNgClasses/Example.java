package testNgClasses;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author: Esra
 */

public class Example {
    WebDriver driver;

    @BeforeClass
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    @AfterClass
    public  void tearDown(){
        driver.quit();
    }
    @Parameters({})
    @Test
    public void login() {
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
    }
    @Test(dependsOnMethods = "login")
    public void addEmployee() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement pim = driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b"));
        action.moveToElement(pim).build().perform();
        Thread.sleep(5000);
        WebElement add = driver.findElement(By.linkText("Add Employee"));
        add.click();
        driver.findElement(By.id("firstName")).sendKeys("Orhan");
        driver.findElement(By.id("lastName")).sendKeys("Demirci");
        String expectedId = driver.findElement(By.cssSelector("#employeeId")).getAttribute("value");
        driver.findElement(By.id("btnSave")).click();
        String actualId =driver.findElement(By.xpath("//input[@id='personal_txtEmployeeId']")).getAttribute("value");
        Assert.assertEquals(actualId,expectedId,"Fail");
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Logout")).click();
    }
}












