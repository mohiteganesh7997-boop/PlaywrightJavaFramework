
package tests;

import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import java.net.URL;
import java.nio.file.Path;

public class HandleFileUploadTest extends TestBase {

    @Test(groups = {"framework"}, description = "Handle File Upload Test")
    public void handleFileUpload() throws Exception {

        page.navigate("https://automationtesting.co.uk/fileupload.html");

        Locator upload = page.locator("#fileToUpload");

        // Get the PDF file from test resources
        URL resource = getClass()
                .getClassLoader()
                .getResource("sample_file_upload_test.pdf");

        if (resource == null) {
            throw new IllegalStateException("PDF file not found!");
        }

        Path filePath = Path.of(resource.toURI());

        // Upload the file
        upload.setInputFiles(filePath);

        page.waitForTimeout(4000);

        System.out.println("File selected successfully!");
        System.out.println("Selected file name: " + filePath.getFileName());
    }
}
