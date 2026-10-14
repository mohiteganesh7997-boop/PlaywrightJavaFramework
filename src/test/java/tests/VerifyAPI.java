package tests;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

    public class VerifyAPI {

        @Test
        public void verifyAPIResponse() {

            Playwright playwright = Playwright.create();
            APIRequestContext request = playwright.request().newContext();
            APIResponse response = request.get( "https://eventhub.rahulshettyacademy.com/login");

            System.out.println("Response status = " + response.status());
            System.out.println("Response status text = " + response.statusText());
            assertThat(response).isOK();
            System.out.println("API response verification passed");

            request.dispose();
            playwright.close();

        }
    }













