package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BasicsTest {

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


    @Test(description = "Create Event - Book that event and verify if it's booked")
    public void createYourOwnEvent(){

        page.getByPlaceholder("you@email.com").fill("playwrightframework123@gmail.com");
        page.getByLabel("Password").fill("Pass@123");
        //page.waitForTimeout(1000);
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Sign In")).click();
       // page.waitForTimeout(2000);
        System.out.println("Page Title is ="+page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        System.out.println("Title verification passed");


        assertThat(page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
        System.out.println("Assertion Passed: Login is successful");

    // Step 1: Create a New Event from Admin page:

        page.navigate("https://eventhub.rahulshettyacademy.com/events");

        // these are page level actions:
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Admin")).click();
        System.out.println("Clicked On Admin button");

        //a tagName means it's a LINK

        //Use NAVIGATION : when you want the link inside the Admin menu or top nav bar.
        // Use CONTENTINFO : when you want the footer version of the link.

        //page.getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK,new Locator.GetByRoleOptions().setName("Manage Events"));
        //page.getByRole(AriaRole.CONTENTINFO)
               //.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Manage Events"))
               //.click();

        page.locator("div.absolute.right-0.top-full a:has-text('Manage Events')").click();
        System.out.println("Clicked On Manage Events link");

        page.getByPlaceholder("Event title").fill("Playwright Learning",new Locator.FillOptions().setTimeout(10000));

        //page.getByPlaceholder("Describe the event…").fill("Enjoy the playwright learning");

                        //  OR use below: Parent to Descendent
        page.locator("#admin-event-form textarea").fill("Enjoy the playwright learning");

        //page.getByLabel("Category").click();
        //page.selectOption("#category","Conference");
                    // OR
        page.getByLabel("Category").selectOption("Conference");

        page.locator("#city").fill("Mumbai");
        // OR
        //page.getByLabel("City").fill("Mumbai");

        page.locator("#venue").fill("Thane city");
        // OR
        //page.getByLabel("Venue").fill("Thane city");

        page.getByLabel("Event Date & Time").fill("2026-11-04T15:34");

        page.getByLabel("Price ($)").fill("1000");

        //page.getByLabel("Total Seats").fill("500");
        page.getByPlaceholder("e.g. 500").fill("500");

        page.getByLabel("Image URL (optional)").fill("https://eventhub.rahulshettyacademy.com/admin/events");
        page.locator("#add-event-btn").click(new Locator.ClickOptions().setTimeout(12000));


        // Define the locator for the toast: 3 ways are given below:

        //toast = page.locator("text=Event created!");
        // Assert the toast text:
        //assertThat(toast).hasText("Event created!");
        // Print the actual toast text:
        //System.out.println("Toast text is: " + toast.innerText());


        // OR > simple to assert toast is given below: using => getByText
        toast = page.getByText("Event created!");
        assertThat(toast).isVisible();     // in Playwright : default timeout for assertions is generally 5 seconds.
        System.out.println("Toast is verified: " + toast.innerText());
                    // OR
//        toast = page.getByText("Event created!");
//        assertThat(toast.innerText()).isEqualTo("Event created!");
//        System.out.println("Toast verified: " + toast);



    // Step 2: Find the newly created event in the Events page
        page.locator("#nav-events").click();
        System.out.println("Clicked on Events section");

        eventCards = page.getByTestId("event-card");
        //page.waitForTimeout(2000);
        assertThat(eventCards.first()).isVisible();
        System.out.println("Total count of event cards=" + eventCards.count());

        // want to check visibility of Newly added Event card:
        targetCard = eventCards.filter(new Locator.FilterOptions().setHasText("Playwright Learning"));

        assertThat(targetCard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));

        //System.out.println("Assertion passed: " + targetCard.innerText());

        // Get number of seats BEFORE booking
        String seatsText = targetCard.getByText("seats").innerText();

        System.out.println("Seats text before booking: " + seatsText);
        int seatsNumBeforeBooking = Integer.parseInt(seatsText.split(" ")[0]);
        System.out.println("Seats before booking: " + seatsNumBeforeBooking);
        targetCard.getByTestId("book-now-btn").click();


        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+")).dblclick(new Locator.DblclickOptions().setTimeout(10000));
        System.out.println("Double clicked on + icon");

        // Book the ticket for a New Event:

        page.locator("#customerName").fill("Robert Junior");
        page.locator("#customer-email").fill("robert123@gmail.com");
        page.locator("#phone").fill("7997012345");

        // Confirm Booking:
        page.locator("#confirm-booking").click(new Locator.ClickOptions().setTimeout(12000));

        assertThat(page.getByText("Your tickets are reserved.")).isVisible();
        System.out.println("Assertion passed:"+ page.getByText("Your tickets are reserved.").innerText());

        bookingRef = page.locator(".booking-ref");
        assertThat(bookingRef).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(12000));
        String bookingRefText = bookingRef.innerText();
        System.out.println("Booking Ref code:"+ bookingRefText);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View My Bookings")).click(new Locator.ClickOptions().setTimeout(12000));
        System.out.println("Clicked on View My Bookings button");


        // Verify Booking History:

        // Verify the same booking reference appears in 'My Bookings' section
        // Below approach: page level approach => means Find this booking reference anywhere on the entire page.
        //assertThat(page.getByText(bookingRefText)).isVisible();
        //System.out.println("Booking Ref code is verified:"+bookingRefText);

        // Below approach: locator based approach => means "From multiple My Bookings cards, find my booking card and verify the Booking Ref of that card."
        Locator bookingCards = page.locator("#booking-card");
        Locator targetBookingCard = bookingCards.filter(new Locator.FilterOptions().setHasText(bookingRefText));
        assertThat(targetBookingCard).isVisible();


        // Verify count reduction of seats
        page.locator("#nav-events").click();
        page.waitForTimeout(1000);

        // Find event cards again after booking
        eventCards = page.getByTestId("event-card");

        // Find our newly created event again
        targetCard = eventCards.filter(
                new Locator.FilterOptions().setHasText("Playwright Learning")
        );

        assertThat(targetCard).isVisible();

        // Get number of seats AFTER booking
        String seatsTextAfterBooking =
                targetCard.getByText("seats").innerText();

        System.out.println("Seats text after booking: " + seatsTextAfterBooking);

        int seatsNumAfterBooking =
                Integer.parseInt(seatsTextAfterBooking.split(" ")[0]);

        System.out.println("Seats after booking: " + seatsNumAfterBooking);

        System.out.println("Seats before booking: " + seatsNumBeforeBooking);
        System.out.println("Seats after booking: " + seatsNumAfterBooking);

        Assert.assertTrue(seatsNumBeforeBooking > seatsNumAfterBooking);

        System.out.println("Assertion passed: Seats decreased successfully");

    }



    @AfterMethod
    public void tearDown(){
        browser.close();
        playwright.close();

    }









}
