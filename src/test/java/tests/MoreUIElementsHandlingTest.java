package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MoreUIElementsHandlingTest {


        Playwright playwright;
        Browser browser;
        Page page;
        BrowserContext context;




        @BeforeMethod(alwaysRun = true)
        public void setUp(){
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            context = browser.newContext();
            page = context.newPage();
            page.navigate("https://rahulshettyacademy.com/loginpagePractise/");


        }

        @AfterMethod(alwaysRun = true)
        public void tearDown(){


        }





        @Test
        public void childWindowHandle(){    //     ->   :this is the lambda operator in Java

//            Locator blinkingTexts = page.locator(".blinkingText");
//            Page newPage = context.waitForPage(() -> blinkingTexts.first().click());  //means When waitForPage is ready, execute blinkingTexts.first().click()
//            //  after above Clicking , page is opening in new tab , so we need to give command for it to Playwright in above code only.
//            newPage.waitForLoadState();
//            String childText = newPage.locator(".red").textContent();
//            // original text: Please email us at mentor@rahulshettyacademy.com with below template to receive response
//            // we need only: mentor@rahulshettyacademy.com      (use split("") method for it)
//            String emailID = childText.split("at ")[1].split(" ")[0];
//            System.out.println(emailID);
//
//            page.locator("#username").fill(emailID);
//            System.out.println(page.locator("#username").inputValue());  // inputValue() reads the current value inside the input field.







        }

        @Test(groups = {"smoke"})
        public void handleRadioBtnCheckboxElements(){

            Locator userRdBtn = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("User"));
            userRdBtn.click();

            page.locator("#okayBtn").click();
            Assert.assertTrue(userRdBtn.isChecked());

            Locator checkBox_terms = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("I Agree to the terms and conditions"));
            checkBox_terms.check();
            Assert.assertTrue(checkBox_terms.isChecked());

            // AriaRole.COMBOBOX : for dropdown/select controls where the user chooses an option from a list.
            page.getByRole(AriaRole.COMBOBOX).selectOption("Teacher");
            // OR you can write like below

            //  Simple rule to use it from DOM :
            //  value="stud" → selectOption("stud")
            //  Visible text Student → setLabel("Student")

            // page.locator("select.form-control").selectOption("teach");

            page.waitForTimeout(3000);

        }








    }















