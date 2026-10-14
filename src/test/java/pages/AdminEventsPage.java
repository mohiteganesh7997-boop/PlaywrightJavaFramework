package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminEventsPage {

    // global variables:
    Page page;


    // Locators:
    private static final String EVENT_TITLE_INPUT  ="Event title";
    private static final String EVENT_TITLE_DESCRIPTION  = "#admin-event-form textarea";
    private static final String CATEGORY_LABEL  ="Category";
    private static final String CITY_SELECTOR  = "#city";
    private static final String VENUE_SELECTOR = "#venue";
    private static final String EVENT_DATE_TIME_LABEL  = "Event Date & Time";
    private static final String PRICE_LABEL  ="Price ($)";
    private static final String TOTAL_SEATS_PLACEHOLDER = "e.g. 500";
    private static final String IMAGE_URL_LABEL  = "Image URL (optional)";
    private static final String ADD_EVENT_BTN = "#add-event-btn";
    private static final String MANAGE_EVENTS_SELECTOR  ="div.absolute.right-0.top-full a:has-text('Manage Events')";
    private static final String SUCCESS_TOAST = "Event created!";

    public AdminEventsPage(Page page){

        this.page = page;


    }

    public void goToAdminEventsPage(){

        page.navigate("https://eventhub.rahulshettyacademy.com/events");

    }


    public void createNewEvent( String eventTitle,
                                String eventDescription,
                                String category,
                                String city,
                                String venue,
                                String eventDateTime,
                                String price,
                                String totalSeats,
                                String imageUrl){

        // these are page level actions:
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Admin")).click();
        System.out.println("Clicked On Admin button");
        page.locator(MANAGE_EVENTS_SELECTOR).click();
        System.out.println("Clicked On Manage Events link");
        page.getByPlaceholder(EVENT_TITLE_INPUT).fill(eventTitle,new Locator.FillOptions().setTimeout(10000));
        //  OR use below: Parent to Descendent
        page.locator(EVENT_TITLE_DESCRIPTION).fill(eventDescription);
        page.getByLabel(CATEGORY_LABEL).selectOption(category);
        page.locator(CITY_SELECTOR).fill(city);
        page.locator(VENUE_SELECTOR).fill(venue);
        page.getByLabel(EVENT_DATE_TIME_LABEL).fill(eventDateTime);
        page.getByLabel(PRICE_LABEL).fill(price);
        page.getByPlaceholder(TOTAL_SEATS_PLACEHOLDER).fill(totalSeats);
        page.getByLabel(IMAGE_URL_LABEL).fill(imageUrl);
        page.locator(ADD_EVENT_BTN).click(new Locator.ClickOptions().setTimeout(12000));
        // Simple to assert toast is given below: using => getByText
       assertThat(page.getByText(SUCCESS_TOAST)).isVisible();
       System.out.println("Assertion passed & Toast is verified: "+ page.getByText(SUCCESS_TOAST).innerText());  // use it for printing toast text


    }




}
