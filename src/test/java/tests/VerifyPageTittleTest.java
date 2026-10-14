package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerifyPageTittleTest {

    @Test
    public void verifyTitle() {
        // 1. Create a Playwright instance
        Playwright playwright = Playwright.create();

        // 2. Launch Chromium in headed mode (headless = false)
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // 3. Create a new Page
        Page page = browser.newPage();

        // 4. Open the practice URL
        page.navigate("https://rahulshettyacademy.com/practice");

        // 5. Wait for 2 seconds
        page.waitForTimeout(2000);

        // 6. Print the page title using page.title()
        System.out.println("Page Title is = " + page.title());

        // 7. Verify the title using Playwright assertion
        assertThat(page).hasTitle("QA Automation Practice Sites | Playwright, Selenium & API Testing");

        // 8. Print Title verification passed
        System.out.println("Title verification passed");

        // 9. Wait for 2 seconds
        page.waitForTimeout(2000);

        // 10. Close the browser
        browser.close();

        // 11. Close Playwright
        playwright.close();
    }
}

