package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    // global variables:
    Page page;
    String base_url;
    // Locators:
    private static final String EMAIL_PLACEHOLDER ="you@email.com";
    private static final String PASSWORD_LABEL ="Password";



    public LoginPage(Page page,String base_url)  {

        this.page = page;
        this.base_url = base_url;
    }

    public DashboardPage loginToApplication(){

    page.navigate(base_url);
    page.getByPlaceholder(EMAIL_PLACEHOLDER).fill("playwrightframework123@gmail.com");
    page.getByLabel(PASSWORD_LABEL).fill("Pass@123");
    //page.waitForTimeout(1000);
    page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Sign In")).click();
    // page.waitForTimeout(2000);
    System.out.println("Page Title is ="+page.title());
    assertThat(page).hasTitle("EventHub — Discover & Book Events");
    System.out.println("Title verification passed");
    DashboardPage dashPage = new DashboardPage(page);
    return dashPage;





    }


}
