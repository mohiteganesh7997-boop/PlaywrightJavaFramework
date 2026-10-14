package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Practice {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeMethod
    public void setUp(){

      playwright = Playwright.create();
      browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      page = browser.newPage();
      page.navigate("https://rahulshettyacademy.com/client/#/auth/login");

    }

    @AfterMethod
    public void tearDown(){

        page.close();
        browser.close();
        playwright.close();

    }



    @Test
    public void clientLogin(){

    page.locator("#userEmail").fill("clienttest1@gmail.com");
    page.getByPlaceholder("enter your passsword").fill("Pass@123");
    page.locator("#login").click();
    page.waitForTimeout(2000);

        Locator orderBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ORDERS"));
        Assert.assertTrue(orderBtn.isVisible());
        System.out.println("Assertion passed");

        //Assert.assertEquals(orderBtn.innerText(),"  ORDERS");
        //Assert.assertEquals(orderBtn.textContent(),"  ORDERS");


        System.out.println("Assertion passed & text is="+orderBtn.textContent());
        System.out.println(("Assertion passed & text is="+orderBtn.innerText()));


        String actURL = page.url();
        System.out.println(actURL);
        Assert.assertEquals(actURL,"https://rahulshettyacademy.com/client/#/dashboard/dash");
        System.out.println("Assertion passed:"+ actURL );

        String pageTitle = page.title();
        System.out.println(pageTitle);
        Assert.assertEquals(pageTitle,"Let's Shop");
        System.out.println("Assertion passed:"+pageTitle);

    }








}
