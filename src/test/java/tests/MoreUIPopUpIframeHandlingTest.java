package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MoreUIPopUpIframeHandlingTest {


    private static final Logger log = LoggerFactory.getLogger(MoreUIPopUpIframeHandlingTest.class);
    Playwright playwright;
    Browser browser;
    Page page;
    BrowserContext context;




    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://rahulshettyacademy.com/AutomationPractice/#top");



    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){


    }

    @Test(groups = {"smoke"})
    public void handlePopUp() {

        // Verify textbox is visible
        assertThat(page.getByPlaceholder("Hide/Show Example")).isVisible();
        page.waitForTimeout(3000);
        // Click Hide
        page.locator("#hide-textbox").click();
        page.waitForTimeout(3000);

        // Verify textbox is hidden
        assertThat(page.getByPlaceholder("Hide/Show Example")).isHidden();
        page.waitForTimeout(3000);


        // =========================
        // ALERT -> OK
        // =========================

        page.onceDialog(dialog -> {

            System.out.println("Alert appeared");
            System.out.println("Dialog type: " + dialog.type());
            System.out.println("Dialog message: " + dialog.message());

            // Keep Alert popup visible for 5 seconds
            page.waitForTimeout(5000);

            // Click OK
            dialog.accept();

            System.out.println("Alert OK clicked");
        });

        page.locator("#alertbtn").click();


        // =========================
        // CONFIRM -> CANCEL
        // =========================

        page.onceDialog(dialog -> {

            System.out.println("Confirm appeared");
            System.out.println("Dialog type: " + dialog.type());
            System.out.println("Dialog message: " + dialog.message());

            // Keep Confirm popup visible for 5 seconds
            page.waitForTimeout(5000);

            // Click Cancel
            dialog.dismiss();

            System.out.println("Confirm Cancel clicked");
        });

        page.locator("#confirmbtn").click();


        page.waitForTimeout(3000);


        page.locator("#mousehover").hover();
        page.waitForTimeout(3000);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Top")).click();

        // Hnadle iframe:
        FrameLocator framePage = page.frameLocator("#courses-iframe");
        framePage.getByRole(AriaRole.LINK,new FrameLocator.GetByRoleOptions().setName("Learning paths")).click();

        String text = framePage.getByRole(AriaRole.HEADING, new FrameLocator.GetByRoleOptions().setName("LEARNING PATHS")).innerText();
        System.out.println(text);



    }

    @Test
    public void screenshotTest(){

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("pagescreenshot.png")));
        Locator displayedEditBox = page.getByPlaceholder("Hide/Show Example");
        displayedEditBox.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("editboxscreenshot.png")));
        page.locator("#hide-textbox").click();



    }




















}
