package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestBase {

    Playwright playwright;
    Browser browser;
    Page page;
    String base_url;

    // Locators:
    Locator toast;
    Locator eventCards;
    Locator targetCard;
    Locator bookingRef;


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        // mvn test -PRegression -Dbrowser=chrome
        // mvn test -PFramework -Dbrowser=chrome
        String browserName = System.getProperty("browser")!=null ?System.getProperty("browser") : prop.getProperty("browser");
        // mvn test -PRegression -Dbrowser=chrome -Denv=dev
        // mvn test -PFramework -Dbrowser=chrome -Denv=dev
        String envName = System.getProperty("env")!=null ?System.getProperty("env") : prop.getProperty("env");
       // String browserName = prop.getProperty("browser");
        playwright = Playwright.create();
        System.out.println("System property browser = "
                + System.getProperty("browser"));

        System.out.println("Browser selected = [" + browserName + "]");

        if("firefox".equals(browserName)){
            browser = playwright.firefox().launch();

        } else if ("safari".equals(browserName)) {
            browser = playwright.webkit().launch();

        } else {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(700));
        }

        page = browser.newPage();
        page.setDefaultTimeout(5000); // this will apply for all locators actions like click/hover/fill/clear/tap etc. as w.k.t. all the elements are associated with the page only
        base_url = prop.getProperty(envName+".base_url");

        PlaywrightAssertions.setDefaultAssertionTimeout(7000);  // globally assertion timeout is 7 sec

    }











}
