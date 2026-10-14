package tests;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class API_Test {


    @Test
    public void e2eApiTest(){

        HashMap<Object,Object> loginPayload = new HashMap<>();  // use to put the object "key & values" related to that API.
        loginPayload.put("email","playwrightframework123@gmail.com");
        loginPayload.put("password", "Pass@123");
//
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();
        APIResponse response = request.post("https://api.eventhub.rahulshettyacademy.com/api/auth/login",
                RequestOptions.create().setData(loginPayload));
//
        Assert.assertTrue(response.ok());
//        // OR
//        // Assert.assertEquals(response.status(), 200);
        System.out.println("Response status is:"+response.status());
        System.out.println("Response status text is:"+response.statusText());
        System.out.println("Response text is:"+response.text());
//
//      //  Why use JsonPath with Playwright? bcz > Playwright's APIResponse gives you the response body:
//      //  String responseBody = response.text();
//
//      //  But, You can then use JsonPath to Extract a particular value from the JSON.
//       // String token = JsonPath.read(body, "$.token");
//       // String userEmail = JsonPath.read(body, "$.user.email");
//
        String body = response.text();
//
        String token = JsonPath.read(body, "$.token");
        Integer userId = JsonPath.read(body, "$.user.id");
        String email = JsonPath.read(body, "$.user.email");

        System.out.println("Token: " + token);
        System.out.println("User ID: " + userId);
        System.out.println("Email: " + email);


// for Create Event API:

        String eventTitle ="API Event Title Postman";
        HashMap<Object,Object> createEventPayload = new HashMap<>();  // use to put the object "key & values" related to that "Create Event" API.
        createEventPayload.put("title",eventTitle); // use of variable here OR can write like below
        createEventPayload.put("description","API Event Title is useful for API testing");
        createEventPayload.put("category","Conference");
        createEventPayload.put("venue","Thane");
        createEventPayload.put("city","Mumbai");
        createEventPayload.put("eventDate","2026-10-16T05:48:00.000Z");
        createEventPayload.put("price","250");
        createEventPayload.put("totalSeats","99");


        APIResponse createEventResponse = request.post("https://api.eventhub.rahulshettyacademy.com/api/events",
                RequestOptions.create().setHeader("Authorization", "Bearer " + token).setData(createEventPayload));

        Assert.assertTrue(createEventResponse.ok(),"Create Event API is worked");

        System.out.println("Response status is:"+createEventResponse.status());
        System.out.println("Response status text is:"+createEventResponse.statusText());
        System.out.println("Response text is:"+createEventResponse.text());

        String body1 = createEventResponse.text();

        Integer eventId = JsonPath.read(body1, "$.data.id");
        System.out.println("Id: " + eventId);

//   GET Event


        APIResponse retrieveEvents = request.get("https://api.eventhub.rahulshettyacademy.com/api/events", RequestOptions.create().setQueryParam("page", "1")
                .setQueryParam("limit", "10").setHeader("Authorization", "Bearer " + token));

        Assert.assertTrue(retrieveEvents.ok(),"Event Retrieval API is worked");

        System.out.println("Response Status is: " + retrieveEvents.status());
        System.out.println("Response Status Text is: " + retrieveEvents.statusText());
        System.out.println("Response text is: " + retrieveEvents.text());

        String retrieveEventsBody = retrieveEvents.text();

        List<Integer> allEventIds = JsonPath.read(retrieveEventsBody, "$.data[*].id");  // use * to get all events Id's.
        System.out.println("All Event IDs: " + allEventIds);

        //Integer specificEventId = allEventIds.get(0);
        //System.out.println("Specific Event ID: " + specificEventId);

        Assert.assertTrue(allEventIds.contains(eventId),"Created event should appear in Events list");


//   Delete Event:

        APIResponse deleteEventResponse = request.delete("https://api.eventhub.rahulshettyacademy.com/api/events/"+eventId,
                RequestOptions.create().setHeader("Authorization", "Bearer " + token));

        //Assert.assertTrue(deleteEventResponse.ok(),"Delete Event API deleted the event successfully");   //  checks whether the response is considered successful.
        Assert.assertEquals(deleteEventResponse.status(),200,"Delete Event API failed");   // checks for exactly HTTP 200.

//  Verify Deletion of event is success: go to Get Events & check that EventId does not exist anymore.

        APIResponse verifyResult = request.get("https://api.eventhub.rahulshettyacademy.com/api/events", RequestOptions.create().setQueryParam("page", "1")
                .setQueryParam("limit", "10").setHeader("Authorization", "Bearer " + token));

        Assert.assertTrue(verifyResult.ok(),"Post-delete events list call should succeed");

        System.out.println("Response Status is: " + verifyResult.status());
        System.out.println("Response Status Text is: " + verifyResult.statusText());
        System.out.println("Response text is: " + verifyResult.text());


        List<Integer> idsAfterDelete = JsonPath.read(verifyResult.text(), "$.data[*].id");  // use * to get all events Id's.
        Assert.assertFalse(idsAfterDelete.contains(eventId),"Deleted event must no longer appear in Events list");
        System.out.println("Deletion of event is verified: Event no longer exist in Events list");














    }










}
