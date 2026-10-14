package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerifyURLTest {


    @Test
    public void verifyPageURL(){

        Playwright playwright =  Playwright.create();
        //Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        // Wait for 3 seconds so you can see the page
        page.waitForTimeout(3000);
        System.out.println("Current URL is = "+ page.url());
        assertThat(page).hasURL("https://eventhub.rahulshettyacademy.com/login");
        System.out.println("URL verification passed");
        page.waitForTimeout(3000);

        browser.close();
        playwright.close();

    }


}
