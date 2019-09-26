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

import java.util.List;

public class PokeTeamTest {

    private ChromeDriver driver;
    static ExtentTest test;
    static ExtentReports extent;

    @Before
    public void setUp(){
        System.setProperty(
                "webdriver.chrome.driver",
                "F:\\SeleniumProjectTests\\src\\test\\java\\resources\\chromedriver.exe"
        );
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }


    /*
    This test is opening the site and choosing the pokemon in slot one, then selecting view all pokemon and selecting a specific pokemon
    in the list and viewing it. It passes if the view page displays the chosen pokemon's name and in this case, Metagross was chosen.
    Future runs may fail as newer pokemon may be added or metagross may be deleted.
     */
    @Test
    public void ViewPokemonTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\VJ\\Documents\\Test Reports\\report.html", false);
        test = extent.startTest("Testing view");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        Thread.sleep(4000);
        WebElement viewButton = driver.findElement(By.id("s1b"));
        viewButton.click();
        Thread.sleep(2000);
        WebElement viewAll = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a"));
        viewAll.click();
        Thread.sleep(2000);
        WebElement meta = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[2]/td[10]/button"));
        meta.click();
        Thread.sleep(2000);
        String name = driver.findElement(By.id("name")).getText();
        System.out.println(name);
        if (name.equals("Metagross")){
            test.log(LogStatus.PASS, "We got to Metagross" );
        }else{
            test.log(LogStatus.FAIL, "Didnt get Metagross" );
        }
        extent.endTest(test);
        extent.flush();

    }

    /*
    This test opens the index page and chooses the option to add a new pokemon. It enters the required details and submits it.
    And finally to check that it has been added, chooses the last entry in the redirected viewPC page and views it to confirm.
     */

    @Test
    public void addPokemonTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\VJ\\Documents\\Test Reports\\report.html", false);
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
        WebElement view = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[12]/td[10]/button"));
        view.click();
        Thread.sleep(2000);
        String tname = driver.findElement(By.id("name")).getText();
        if (tname.equals("Rayquaza")){
            test.log(LogStatus.PASS, "We added Rayquaza" );
        }else{
            test.log(LogStatus.FAIL, "Didnt get Rayquaza" );
        }
        extent.endTest(test);
        extent.flush();

    }

    /*
    This test tests exchange by choosing the pokemon in the 1st slot, then press the exchange button on the view page
    and switching the current pokemon with the first pokemon in the list ie Milotic. This test passes if Milotic is in one
    of the six slots on the index page. Future runs may fail if Milotic is missing.
     */

    @Test
    public void exchangeTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\VJ\\Documents\\Test Reports\\report.html", false);
        test = extent.startTest("Testing exchange");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        WebElement viewButton = driver.findElement(By.xpath("//*[@id=\"s1b\"]"));
        viewButton.click();
        Thread.sleep(2000);
        WebElement exchangeButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]"));
        exchangeButton.click();
        Thread.sleep(2000);
        WebElement milo = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[1]/td[11]/button"));
        milo.click();
        Thread.sleep(2000);
        for(int i = 0; i < 6; i++){
            String tname = driver.findElement(By.id("s"+(i+1))).getText();
            if (tname.equals("Milotic")){
                test.log(LogStatus.PASS, "We switched in Milotic" );
                break;
            }
        }
        extent.endTest(test);
        extent.flush();

    }

    /*
    This test is used to delete the last pokemon(for testing purposes) from the list of pokemon. It chooses the pokemon to be deleted(in this case Infernape is the second to last in the list)
    and deletes it from the table. If the name still exists then the test fails. Else if its infernape which is now the last pokemon, it passes.
     */
    @Test
    public void deleteTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\VJ\\Documents\\Test Reports\\report.html", false);
        test = extent.startTest("Testing delete");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        WebElement pc = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a"));
        pc.click();
        WebElement todel = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[11]/td[8]/button"));
        todel.click();
        String name = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[11]/td[2]")).getText();
        if(name.equals("Infernape") ){
            test.log(LogStatus.PASS, "We deleted the last pokemon");
        }else{
            test.log(LogStatus.FAIL, "Still there" );
        }
        extent.endTest(test);
        extent.flush();

    }

    /*
    This test is used to test the editing of moves for a pokemon by first choosing to view entire list of pokemon. Then
    it picks the first item in the list to edit and brings up the modal. It then inputs the information and submits it.
    The test result is based on if the move in column move 1 of the chosen pokemon has been changed to the specified one.
     */

    @Test
    public void EditPokemonTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\VJ\\Documents\\Test Reports\\report.html", false);
        test = extent.startTest("Testing edit");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        Thread.sleep(2000);
        WebElement viewAllButton = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a"));
        viewAllButton.click();
        Thread.sleep(2000);
        WebElement edit = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[1]/td[9]/button"));
        edit.click();
        Thread.sleep(2000);
        WebElement m1 = driver.findElement(By.xpath("//*[@id=\"tableid\"]/table/tbody/tr[3]/td[2]/input"));
        m1.sendKeys("Recover");
        Thread.sleep(2000);
        WebElement m2 = driver.findElement(By.xpath("//*[@id=\"tableid\"]/table/tbody/tr[4]/td[2]/input"));
        m2.sendKeys("Surf");
        Thread.sleep(2000);
        WebElement m3 = driver.findElement(By.xpath("//*[@id=\"tableid\"]/table/tbody/tr[5]/td[2]/input"));
        m3.sendKeys("Toxic");
        Thread.sleep(2000);
        WebElement m4 = driver.findElement(By.xpath("//*[@id=\"tableid\"]/table/tbody/tr[6]/td[2]/input"));
        m4.sendKeys("Blizzard");
        Thread.sleep(2000);
        WebElement save = driver.findElement(By.xpath("//*[@id=\"pcsubmit\"]"));
        save.click();
        Thread.sleep(2000);
        String mname = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[1]/td[7]")).getText();
        if (mname.equals("Blizzard")){
            test.log(LogStatus.PASS, "We changed it to Blizzard");
        }else{
            test.log(LogStatus.FAIL, "We changed it to Blizzard");
        }
        extent.endTest(test);
        extent.flush();
    }

    /*
    This test tests the search function of the table by going to the list PC page and entering the name of a pokemon into the
    field(Arcanine in this case) then checking if the returned result matches the name that should be returned.
     */

    @Test
    public void searchTest() throws InterruptedException {
        extent = new ExtentReports("C:\\Users\\VJ\\Documents\\Test Reports\\report.html", false);
        test = extent.startTest("Testing search");
        driver.manage().window().maximize();
        driver.get("http://35.210.100.46/index.html");
        WebElement pc = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a"));
        pc.click();
        WebElement search = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
        search.sendKeys("Arcanine");
        String name = driver.findElement(By.xpath("//*[@id=\"tableBody\"]/tr[4]/td[2]")).getText();
        if(name.equals("Arcanine") ){
            test.log(LogStatus.PASS, "We found the searched pokemon");
        }else{
            test.log(LogStatus.FAIL, "Nope" );
        }
        extent.endTest(test);
        extent.flush();

    }



}
