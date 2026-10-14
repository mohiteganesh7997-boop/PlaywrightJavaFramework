package tests;

import java.util.regex.Pattern;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EventHubBookingPageObjectTest extends TestBase {

    private static final String APP_URL = "https://eventhub.rahulshettyacademy.com/";
    private static final String USERNAME = "playwrightframework123@gmail.com";
    private static final String PASSWORD = "Pass@123";

    @Test(description = "Login, open My Bookings, cancel first booking, verify the record disappears")
    public void cancelMyBooking() {

        // Step 1: Navigate to app and login
        page.navigate(APP_URL);
        page.locator("input#email").fill(USERNAME);
        page.locator("input#password").fill(PASSWORD);
        page.locator("button#login-btn").click();

        // Step 2: Verify Home page is loaded - Browse Events checkpoint
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
        System.out.println("Home page loaded successfully");

        // Step 3: Click on My Bookings tab
        page.locator("a[href='/bookings']").nth(1).click();
        Locator bookingCards = page.locator("#booking-card");
        assertThat(bookingCards.first()).isVisible();
        System.out.println("My Bookings page loaded");

        // Step 4: View Details of the first booking
        Locator firstBookingCard = bookingCards.first();
        String bookingRefText = firstBookingCard.locator(".booking-ref").innerText().trim();
        System.out.println("First booking ref: " + bookingRefText);

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("View Details")
        ).first().click();
        page.waitForURL("**/bookings/*");
        assertThat(page).hasURL(Pattern.compile(".*/bookings/\\d+"));
        System.out.println("Booking details page displayed");

        // Step 5: Cancel Booking
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel Booking")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, cancel it")).click();

        // Step 6: Verify booking is cancelled and record is gone
        page.waitForURL("**/bookings");
        Locator cancelledBookingCard = page.locator("#booking-card")
                .filter(new Locator.FilterOptions().setHasText(bookingRefText));
        assertThat(cancelledBookingCard).hasCount(0);
        System.out.println("Booking cancelled successfully and record disappeared");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
