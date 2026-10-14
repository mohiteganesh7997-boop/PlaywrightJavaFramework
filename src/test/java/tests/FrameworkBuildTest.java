package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public class FrameworkBuildTest extends TestBase {


    @Test(description = "Create Event - Book that event and verify if it's booked")
    public void createYourOwnEvent(){

                String eventTitle = "Playwright Learning";
        // Step 1: Create a New Event from Admin page:

        LoginPage loginPage = new LoginPage(page,base_url);  // parameterized constructor
        DashboardPage dashPage = loginPage.loginToApplication();
        dashPage.landOnDashboardPage();
        AdminEventsPage adminEventsPage = new AdminEventsPage(page);
        adminEventsPage.goToAdminEventsPage();
        // Step 1: Create a New Event from Admin page:
        adminEventsPage.createNewEvent( eventTitle,
                "Enjoy the playwright learning",
                "Conference",
                "Mumbai",
                "Thane city",
                "2026-11-04T15:34",
                "1000",
                "500",
                "https://eventhub.rahulshettyacademy.com/admin/events");



        // Step 2: Find the newly created event in the Events page
        EventsPage eventsPage = new EventsPage(page);
        eventsPage.goToEventsTab();
        Locator targetCard = eventsPage.findEventCard(eventTitle);
        int seatsNumBeforeBooking = eventsPage.getSeatsCount(targetCard);
        BookingFormPage bookingFormPage = eventsPage.clickingOnBookNowBtn(targetCard);
        bookingFormPage.fillAndConfirm("Robert Junior","robert123@gmail.com", "7997012345");


        // Step 3: Book the ticket for a New Event:


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
                new Locator.FilterOptions().setHasText(bookingRefText)
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
