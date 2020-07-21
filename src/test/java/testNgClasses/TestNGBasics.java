package testNgClasses;

import org.testng.annotations.*;

public class TestNGBasics {

    @BeforeSuite
    public void setUp(){
        System.out.println("driver manager is installed--BeforeSuite");
        //WebDrivreManager.chrome.setup
    }
    @BeforeTest
    public void launchBrowser(){
        System.out.println("launch chrome browser--Before TEst");
        //WebDriver driver=new ChromeDriver();
    }
    @BeforeClass
    public void login(){
        System.out.println("enter to browser--BeforeClass");
    }
    @BeforeMethod   // @BeforeEach in Junit
    public void enterURL(){
        System.out.println("enter URL--Before method");
        //driver.get("http://google.com/")
    }
    @Test
    public void googleTitleTest(){
        System.out.println("google title test--test");
    }
    @Test
    public void googleLogo(){
        System.out.println("Google Logo Test--test");
    }
    @AfterMethod   //@AfterEach in Junit
    public void refresh(){
        System.out.println("refreshing the home page--after method");
    }
    @AfterClass
    public void closeBrowser(){
        System.out.println("close the browser--after class");
    }
    @AfterTest
    public void deleteAllCookies(){
        System.out.println("delete cookies-- Test");
    }
    @AfterSuite
    public void generateTestReport(){
        System.out.println("generating test report--After Suite");
    }
}
