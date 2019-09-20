package com.qa;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PokeTeamTest {

    private ChromeDriver driver;
    static ExtentTest test;
    static ExtentReports extent;

    @Before
    public void setUp(){
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Admin\\Documents\\Week-6\\FirstSeleniumTest\\src\\test\\java\\resources\\chromedriver.exe"
        );
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void ViewPokemonTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\Admin\\Documents\\Week-6\\Test Reports\\PokeTest.html", true);
        test = extent.startTest("Testing view");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        Thread.sleep(2000);
        WebElement viewButton = driver.findElement(By.id("s1b"));
        viewButton.click();
        Thread.sleep(2000);
        WebElement viewAll = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a"));
        viewAll.click();
        Thread.sleep(2000);
        WebElement meta = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[2]/td[10]/button"));
        meta.click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        if (name.getText() == "Metagross"){
            test.log(LogStatus.PASS, "We got to Metagross" );
        }else{
            test.log(LogStatus.FAIL, "Didnt get Metagross" );
        }
        extent.endTest(test);
        extent.flush();

    }

    @Test
    public void addPokemonTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\Admin\\Documents\\Week-6\\Test Reports\\PokeTest.html", true);
        test = extent.startTest("Testing add");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        Thread.sleep(2000);
        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a"));
        addButton.click();
        Thread.sleep(2000);
        WebElement name = driver.findElement(By.xpath("/html/body/div/form/input"));
        name.sendKeys("Rayquaza");
        Thread.sleep(2000);
        WebElement pid = driver.findElement(By.xpath("/html/body/div/form/div[1]/input[1]"));
        pid.sendKeys("384");
        Thread.sleep(2000);
        WebElement m1 = driver.findElement(By.xpath("/html/body/div/form/div[1]/input[2]"));
        m1.sendKeys("Fly");
        Thread.sleep(2000);
        WebElement m2 = driver.findElement(By.xpath("/html/body/div/form/div[1]/input[3]"));
        m2.sendKeys("ExtremeSpeed");
        Thread.sleep(2000);
        WebElement m3 = driver.findElement(By.xpath("/html/body/div/form/div[1]/input[4]"));
        m3.sendKeys("Dragon Claw");
        Thread.sleep(2000);
        WebElement m4 = driver.findElement(By.xpath("/html/body/div/form/div[1]/input[5]"));
        m4.sendKeys("Acrobatic");
        Thread.sleep(2000);
        WebElement submit = driver.findElement(By.xpath("/html/body/div/form/div[2]/input"));
        submit.click();
        Thread.sleep(2000);
        WebElement tname = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        if (tname.getText() == "Rayquaza"){
            test.log(LogStatus.PASS, "We added Rayquaza" );
        }else{
            test.log(LogStatus.FAIL, "Didnt get Rayquaza" );
        }
        extent.endTest(test);
        extent.flush();

    }

    @Test
    public void exchangeTest(){
        extent = new ExtentReports("C:\\Users\\Admin\\Documents\\Week-6\\Test Reports\\PokeTest.html", true);
        test = extent.startTest("Testing exchange");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        WebElement viewButton = driver.findElement(By.id("s1b"));
        viewButton.click();
    }



}
