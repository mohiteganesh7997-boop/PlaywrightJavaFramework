package tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

public class FileUploadTest extends TestBase {

    @Test
    public void uploadFileTest() {

        // 1. Navigate to the application
        page.navigate("https://dlptest.com/http-post/?utm_source=chatgpt.com",
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.LOAD)
        );


        // 2. Locate the file-upload element using ID
        Locator upload = page.locator("#pt-file");
        // Alternative locator:
        // Locator upload = page.locator("input[type='file']");

        // 3. Scroll the file-upload element into view
        upload.scrollIntoViewIfNeeded();

        // 4. Store the file path
        Path filePath = Paths.get("C:/Users/GANESH/Downloads/Test_file_upload.pdf");

        // 5. Upload the file
        upload.setInputFiles(filePath);

        // 6. Print upload information
        System.out.println("File uploaded successfully!");
        System.out.println("Uploaded file name: " + filePath.getFileName());
    }
}