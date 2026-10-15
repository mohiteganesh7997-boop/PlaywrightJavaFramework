package tests;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HandleFileUploadTest extends TestBase {

    @Test
    public void handleFileUpload(){

        page.navigate("https://automationtesting.co.uk/fileupload.html");

        Locator upload = page.locator("#fileToUpload");
        Path filePath = Paths.get("C:/Users/GANESH/Downloads/sample_file_upload_test.pdf");
        upload.setInputFiles(filePath);
        page.waitForTimeout(4000);

        System.out.println("File uploaded successfully!");
        System.out.println("Uploaded file name is:"+filePath.getFileName());








    }










}
