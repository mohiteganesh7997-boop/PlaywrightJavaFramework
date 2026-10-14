package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFileUpload {

   @Test
   public void uploadFileTest(){

      Playwright playwright = Playwright.create();
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      Page page = browser.newPage();
      page.navigate("https://dlptest.com/http-post/?utm_source=chatgpt.com", new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));

      Locator upload = page.locator("#pt-file");
      upload.scrollIntoViewIfNeeded();
      Path filePath = Paths.get("C:/Users/GANESH/Downloads/sample_file_upload_test.pdf");
      upload.setInputFiles(filePath);
      page.waitForTimeout(3000); // Wait for 3 seconds to ensure the file is uploaded

      System.out.println("File Uploaded Successfully!");
      System.out.println("Uploaded file name is: "+filePath.getFileName());


      page.close();
      browser.close();
      playwright.close();



   }


}
