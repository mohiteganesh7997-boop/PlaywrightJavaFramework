package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
// Copilot & claude code
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MockWebTest {


    Playwright playwright;
    Browser browser;
    Page page;

    // Locators:
    Locator toast;
    Locator eventCards;
    Locator targetCard;
    Locator bookingRef;


    @BeforeMethod
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.setDefaultTimeout(5000); // this will apply for all locators actions like click/hover/fill/clear/tap etc. as w.k.t. all the elements are associated with the page only
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        PlaywrightAssertions.setDefaultAssertionTimeout(7000);  // globally assertion timeout is 7 sec

    }


    @Test(description = "Sandbox banner is shown when 6 events are returned")
    public void createYourOwnEvent() {

        page.getByPlaceholder("you@email.com").fill("playwrightframework123@gmail.com");
        page.getByLabel("Password").fill("Pass@123");
        //page.waitForTimeout(1000);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        // page.waitForTimeout(2000);
        System.out.println("Page Title is =" + page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        System.out.println("Title verification passed");


        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
        System.out.println("Assertion Passed: Login is successful");

        // Step 1: Create a New Event from Admin page:

//  Mocking/Modifying response:   use of  "route.fulfill();"

        page.route("**/api/events**", route -> route.fulfill(new Route.FulfillOptions().setPath(Paths.get("src/test/resources/events_6.json"))));
        page.navigate("https://eventhub.rahulshettyacademy.com/events");
        //page.waitForTimeout(7000);

        Locator totalEventCards = page.getByTestId("event-card");
        assertThat(totalEventCards.first()).isVisible();  // we are using it to wait to load the first card and also help to appear all 6 cards so our next assertion steps will get time to passed as expected

        Assert.assertEquals(totalEventCards.count(), 6);
        assertThat(page.getByText("6 custom events")).isVisible();
        System.out.println("Assertion Passed: Sandbox banner with '6 custom events' is visible");


        page.route("**/api/events**", route -> route.fulfill(new Route.FulfillOptions().setPath(Paths.get("src/test/resources/events_4.json"))));
        page.navigate("https://eventhub.rahulshettyacademy.com/events");  // refresh page again

        Locator totalEventCards1 = page.getByTestId("event-card");
        assertThat(totalEventCards.first()).isVisible();  // we are using it to wait to load the first card and also help to appear all 6 cards so our next assertion steps will get time to passed as expected
        page.waitForTimeout(4000);
        Assert.assertEquals(totalEventCards1.count(), 4);
        assertThat(page.getByText("4 custom events")).isHidden();
        System.out.println("Assertion Passed: Sandbox banner with '4 custom events' is NOT visible");

    }




    @Test
    public void routeResumeTest(){

//  Mocking/Modifying request:   use of  "route.resume();"

        // login code :
        page.getByPlaceholder("you@email.com").fill("playwrightframework123@gmail.com");
        page.getByLabel("Password").fill("Pass@123");
        //page.waitForTimeout(1000);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        // page.waitForTimeout(2000);
        System.out.println("Page Title is =" + page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        System.out.println("Title verification passed");


        // actual required steps:
        page.locator("#nav-bookings").click();

        page.route("**/api/bookings/**",route -> route.resume(new Route.ResumeOptions().setUrl("https://api.eventhub.rahulshettyacademy.com/api/bookings/20936")));
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("View Details")).first().click();
        //assertThat(page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Access Denied"))).isVisible();
        // Or you can simply write like below:
        assertThat(page.getByText("Access Denied")).isVisible();
        System.out.println("Assertion passed");



    }



}




// The learning goal is:

// Intercept a real API request → prevent the real response from reaching the UI → inject our own fake response →
// verify how the application behaves for edge cases.


// --------------------------------------------------------------------
//      Login
//        ↓
//      Sign In
//        ↓
//      Verify login
//        ↓
//      page.route("**/api/events**")
//        ↓
//      Navigate to /events
//        ↓
//      Application calls  /api/events
//        ↓
//      Playwright intercepts it
//        ↓
//      events_6.json    (json file name)
//        ↓
//      Application receives fake 6-event response
//        ↓
//      UI displays 6 events
//        ↓
//      ASSERT
// --------------------------------------------------------------------





//  page.route("**/api/events**",
//      route -> route.fulfill(
//          new Route.FulfillOptions()
//            .setPath(Paths.get("src/test/resources/events_6.json"))
//          )
//      );

// The above code means:

// When the browser requests a URL matching **/api/events**, don't use the actual server response.
// Fulfill the request using my local JSON file.




// Interview-ready explanation:

//If an interviewer asks:

// @ "Why did you use page.route()?"
//You can say:

//"The UI behavior was dependent on API response data. I intercepted the /api/events request using Playwright's page.route() and fulfilled it with controlled local JSON responses.
// This allowed me to deterministically test different data conditions, such as 6 events where the banner should appear and 4 events where it should not appear."



//And if they ask:

// @ "Why mock instead of using the real API?"
//Say:

//"Mocking gives deterministic test data. The real API may return different data at different times, making edge-case validation unreliable.
// By mocking the response, I can specifically test the application's behavior for each scenario."