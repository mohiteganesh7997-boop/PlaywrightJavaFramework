package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class MoreUIValidationTest {
    Playwright playwright;
    Browser browser;
    Page page;
    BrowserContext context;




    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();

        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/");


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));

    }





    @Test
    public void childWindowHandle(){    //     ->   :this is the lambda operator in Java

        Locator blinkingTexts = page.locator(".blinkingText");
        Page newPage = context.waitForPage(() -> blinkingTexts.first().click());  //means When waitForPage is ready, execute blinkingTexts.first().click()
  //  after above Clicking , page is opening in new tab , so we need to give command for it to Playwright in above code only.
        newPage.waitForLoadState();
        String childText = newPage.locator(".red").textContent();
      // original text: Please email us at mentor@rahulshettyacademy.com with below template to receive response
        // we need only: mentor@rahulshettyacademy.com      (use split("") method for it)
        String emailID = childText.split("at ")[1].split(" ")[0];
        System.out.println(emailID);

        page.locator("#username").fill(emailID);
        System.out.println(page.locator("#username").inputValue());  // inputValue() reads the current value inside the input field.







    }










}
