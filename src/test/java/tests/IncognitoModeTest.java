package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class IncognitoModeTest {

    @Test
    public void verifyIncognitoMode() throws InterruptedException {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );

        // Create isolated browser context
        BrowserContext context = browser.newContext();

        Page page = context.newPage();

        page.navigate("https://eventhub.rahulshettyacademy.com/login");

        System.out.println("Page opened");
        System.out.println("Current URL = " + page.url());

        // Keep browser open so you can see it
        Thread.sleep(10000);

        context.close();
        browser.close();
        playwright.close();
    }
}