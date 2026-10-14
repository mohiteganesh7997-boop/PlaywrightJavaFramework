package tests;
//   https://rahulshettyacademy.com/practice
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FullWebPageSSTest {

    @Test
    public void takeFullPageScreenshot() {
        // 1. Create a Playwright instance
        Playwright playwright = Playwright.create();

        // 2. Launch Chromium in headed mode (headless = false)
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // 3. Create a new page
        Page page = browser.newPage();

        // 4. Open the URL
        page.navigate("https://www.amazon.in/");

        // 5. Wait for the page to load
        page.waitForTimeout(2000);

        // 6. Define the screenshot path inside the screenshots folder
        Path screenshotPath = Paths.get(System.getProperty("user.dir"), "screenshots", "amazonFullPage.png");

        // 7. Capture a full page screenshot and save it in screenshots folder
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(screenshotPath)
                .setFullPage(true));

        // 8. Print confirmation
        System.out.println("Full page screenshot saved at: " + screenshotPath);

        // 9. Close the browser
        browser.close();

        // 10. Close Playwright
        playwright.close();
    }
}

