package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoatorsPracticeTwo {

    @Test
    public void useOfLocators(){

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        System.out.println("Page Title is="+ page.title());
        assertThat(page).hasTitle("OrangeHRM");
        System.out.println("Title is verified");

        page.getByPlaceholder("Username").fill("Admin");
        page.getByPlaceholder("Password").fill("admin123");
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName(" Login ")).click();
        page.waitForTimeout(2000);
        assertThat(page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Dashboard")));
        System.out.println("Assertion Passed: Login successful");



        browser.close();
        playwright.close();



    }










}
