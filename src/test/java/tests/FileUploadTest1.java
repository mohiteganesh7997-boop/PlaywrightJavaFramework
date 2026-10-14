package tests;


import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

public class FileUploadTest1 extends TestBase {

    @Test
    public void uploadFileTest() {

        // Navigate to the website and wait for page load
        page.navigate("https://rahulshettyacademy.com/upload-download-test/", new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.LOAD));
        // Locate the file-upload element
        Locator upload = page.locator("#fileinput");
        // Scroll the element into view
        upload.scrollIntoViewIfNeeded();
        // Store the file path
        Path filePath = Paths.get("C:/Users/GANESH/Downloads/OOPS_One_Stop_Master_Sheet.docx");
        // Upload the file
        upload.setInputFiles(filePath);

        // Print the file name
        System.out.println("File selected successfully!");
        System.out.println("Uploaded file name: " + filePath.getFileName());
    }
}

