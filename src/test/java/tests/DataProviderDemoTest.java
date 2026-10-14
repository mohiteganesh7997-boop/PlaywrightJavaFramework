package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;


//  if you have smaller test data then prefer this approach:

public class DataProviderDemoTest {

    @DataProvider(name="basicData")  // Multi-dimensional array of objects
    public Object[][] basicData(){

            return new Object[][]  {    {"user1@yahoo.com","Password@123"},
                                        {"user1@gmail.com","Password@123"},
                                                                             };

    }

    @Test(dataProvider = "basicData")
    public void testFillForm(String email, String password){

        System.out.println(email);
        System.out.println(password);

    }




//  if you have longer test data then prefer this approach:

    @DataProvider(name="hashMapData")  // Multi-dimensional array of objects
    public Object[][] hashMapData(){

        HashMap<String,String> user1  = new HashMap<>();
        user1.put("email","user1@yahoo.com");
        user1.put("password","Password@123");

        HashMap<String,String> user2 = new HashMap<>();
        user2.put("email","user1@gmail.com");
        user2.put("password","Password@12345");


        return new Object[][] {  {user1},{user2}  };

    }


    @DataProvider(name = "jsonData")
    public Object[][] jsonData() throws IOException {

        // 1. Read JSON file
        String jsonContent = new String(
                Files.readAllBytes(
                        Paths.get(System.getProperty("user.dir") + "/src/test/resources/testData_TC1.json")));

        // 2. Convert JSON → List of HashMaps
        Type type = new TypeToken<List<HashMap<String, String>>>() {}.getType();

        List<HashMap<String, String>> list =
                new Gson().fromJson(jsonContent, type);

        // 3. Convert List → Object[][]
        Object[][] table = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            table[i][0] = list.get(i);
        }

        return table;
    }

    @Test(dataProvider = "jsonData")
    public void testWithHashmap(HashMap<String,String> data){

        System.out.println(data.get("email"));
        System.out.println(data.get("password"));


    }

}
