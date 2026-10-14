package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class DataProviderConcept {

    // for smaller test data: use this approach

    @DataProvider(name = "basicData")
    public Object[][] basicData(){

        return new Object[][]  {    {"user1@gmail.com","Pass@123"},
                                    {"user2@yahoo.com","Pass@12345"}    };

    }

    @Test(dataProvider = "basicData")
    public void testFillForm(String email,String pwd){

        System.out.println(email);
        System.out.println(pwd);

    }



    // for longer test data: use this approach

    @DataProvider(name ="hashMapData")
    public Object[][] hashMapData(){

        HashMap<String,String> user1 = new HashMap<>();
        user1.put("email","user1@gmail.com");
        user1.put("password","Pass@123");

        HashMap<String,String> user2 = new HashMap<>();
        user2.put("email","user2@yahoo.com");
        user2.put("password","Pass@12345");



        return new Object[][]  { {user1}, {user2} };
    }


    @Test(dataProvider = "hashMapData")
    public void useOfHashMapData(HashMap<String,String >data){

        System.out.println(data.get("email"));
        System.out.println(data.get("password"));

    }


}
