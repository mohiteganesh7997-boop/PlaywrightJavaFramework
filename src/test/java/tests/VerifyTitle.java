package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerifyTitle {


   @Test
    public void verifyTitle(){

       Playwright playwright = Playwright.create();
       Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
       Page page = browser.newPage();
       page.navigate("https://eventhub.rahulshettyacademy.com/login");
       page.waitForTimeout(2000);
       System.out.println("Page Title is ="+page.title());
       assertThat(page).hasTitle("EventHub — Discover & Book Events");
       System.out.println("Title verification passed");
       page.waitForTimeout(2000);
        browser.close();
        playwright.close();

   }
}
