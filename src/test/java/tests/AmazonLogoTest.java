package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AmazonLogoTest {

    @Test
    public void takeAmazonLogoScreenshot() {
        // 1. Create a Playwright instance
        Playwright playwright = Playwright.create();

        // 2. Launch Chromium in headed mode (headless = false)
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // 3. Create a new page
        Page page = browser.newPage();

        // 4. Open Amazon homepage
        page.navigate("https://www.amazon.in/");

        // 5. Wait for the page to load
        page.waitForTimeout(3000);

        // 6. Find the Amazon logo element
        Locator amazonLogo = page.locator("#nav-logo-sprites");

        // 7. Define screenshot path inside screenshots folder
        Path screenshotPath = Paths.get(System.getProperty("user.dir"), "screenshots", "amazonLogoSS.png");

        // 8. Capture only the logo element screenshot and save it
        amazonLogo.screenshot(new Locator.ScreenshotOptions().setPath(screenshotPath));

        // 9. Print confirmation
        System.out.println("Amazon logo screenshot saved at: " + screenshotPath);

        // 10. Close the browser
        browser.close();

        // 11. Close Playwright
        playwright.close();
    }
}

