package testNgClasses;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author: Esra
 */

public class DataProviderExample {
    WebDriver driver;

    @BeforeMethod
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    @AfterMethod
    public  void tearDown(){
        driver.quit();
    }
    @Parameters({})

    @Test(dataProvider = "getData")
    public void addEmployee(String firstname,String lastname) throws InterruptedException, IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("C:\\Users\\12812\\TestNG_IQ\\src\\test\\java\\suite\\loginCredentials.properties");
        prop.load(fis);
        String userName=prop.getProperty("userName");
        String password=prop.getProperty("password");


        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
        Actions action = new Actions(driver);
        WebElement pim = driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b"));
        action.moveToElement(pim).build().perform();
        Thread.sleep(5000);
        WebElement add = driver.findElement(By.linkText("Add Employee"));
        add.click();
        driver.findElement(By.id("firstName")).sendKeys(firstname);
        driver.findElement(By.id("lastName")).sendKeys(lastname);
        String expectedId = driver.findElement(By.cssSelector("#employeeId")).getAttribute("value");
        driver.findElement(By.id("btnSave")).click();
        String actualId =driver.findElement(By.xpath("//input[@id='personal_txtEmployeeId']")).getAttribute("value");
        Assert.assertEquals(actualId,expectedId,"Fail");
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Logout")).click();
    }
    @DataProvider
    public Object[][] getData(){
       Object [][] data={{"Ali","Donmez"},{"Veli","Ucar"},{"Ayse","Mutlu"}};
       return data;
    }
}












