package tests;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

public class NegativeAPIScenarioWithStatusCodeAndTextTest {

    @Test
    public void verifyAPIStatusAndText(){

        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();
        APIResponse response = request.get("https://eventhub.rahulshettyacademy.com/users/999999");

        System.out.println("Response status is="+ response.status());
        System.out.println("Response status text is="+ response.statusText());

       assertEquals(response.status(),404);
       System.out.println("Negative API scenario passed");

        request.dispose();
        playwright.close();



    }






}
