package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EventsPage {

    Page page;


    public EventsPage(Page page){

            this.page = page;
    }




    public void goToEventsTab(){

        page.locator("#nav-events").click();

    }

    public Locator waitForEventsToLoad(){

        Locator eventCards = page.getByTestId("event-card");
        assertThat(eventCards.first()).isVisible();
        return eventCards;
    }

    public Locator findEventCard(String titleCard){

        Locator eventCards = waitForEventsToLoad();
        Locator targetCard = eventCards.filter(new Locator.FilterOptions().setHasText(titleCard));
        assertThat(targetCard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        return targetCard;
    }

    public int getSeatsCount(Locator targetCard){

        // assertThat(targetCard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));


        String seatsText = targetCard.getByText("seats").innerText();
        System.out.println("Seats text before booking: " + seatsText);
        return Integer.parseInt(seatsText.split(" ")[0]);

    }

    public BookingFormPage clickingOnBookNowBtn(Locator targetCard)
    {

        targetCard.getByTestId("book-now-btn").click();
        return new BookingFormPage(page);



    }
}
