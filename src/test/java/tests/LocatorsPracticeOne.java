package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocatorsPracticeOne {

    @Test
    public void learnUseOfLocators(){

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        page.waitForTimeout(2000);

        //page.getByLabel("Email").fill("playwrightframework123@gmail.com");
        //page.waitForTimeout(1000);
        page.getByPlaceholder("you@email.com").fill("playwrightframework123@gmail.com");
        page.getByLabel("Password").fill("Pass@123");
        page.waitForTimeout(1000);
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Sign In")).click();
        page.waitForTimeout(2000);

        assertThat(page.getByText("Browse Events →")).isVisible();
        System.out.println("Assertion Passed: Login successful");

        browser.close();
        playwright.close();








    }









}
