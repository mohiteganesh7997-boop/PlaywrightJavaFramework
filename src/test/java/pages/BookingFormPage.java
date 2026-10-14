package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BookingFormPage {

    Page page;

    // locators:
    private static final String CUSTOMER_NAME_PLACEHOLDER = "#customerName";
    private static final String CUSTOMER_EMAIL_PLACEHOLDER = "#customer-email";
    private static final String CUSTOMER_PHONE_PLACEHOLDER = "#phone";
    private static final String CONFIRM_BOOKING_BTN = "#confirm-booking";
    private static final String SUCCESS_TOAST = "Your tickets are reserved.";



    public BookingFormPage(Page page){

        this.page = page;
    }

    public void fillAndConfirm(String fullName, String email, String phone){

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+")).dblclick(new Locator.DblclickOptions().setTimeout(10000));
        System.out.println("Double clicked on + icon");

        page.locator(CUSTOMER_NAME_PLACEHOLDER).fill(fullName); //
        page.locator(CUSTOMER_EMAIL_PLACEHOLDER).fill(email); //
        page.locator(CUSTOMER_PHONE_PLACEHOLDER).fill(phone); //
        // Confirm Booking:
        page.locator(CONFIRM_BOOKING_BTN).click(new Locator.ClickOptions().setTimeout(12000));

        assertThat(page.getByText(SUCCESS_TOAST)).isVisible();
        System.out.println("Assertion passed & Toast is verified: "+ page.getByText(SUCCESS_TOAST).innerText());

    }




}
