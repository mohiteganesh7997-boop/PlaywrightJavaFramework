package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;



public class DashboardPage {

    Page page;

    public DashboardPage(Page page) {

        this.page = page;
    }


    public void landOnDashboardPage() {
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
        System.out.println("Assertion Passed: Login is successful");

    }


}
