package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class DataProviderImplementation {

    @DataProvider(name = "basicData")
    public Object [][] basicData(){

        return new Object [][]  { {"user1@yahoo.com","Pass@123"},{"user2@gmail.com","Pass@12345"} };

    }

    @Test(dataProvider = "basicData")
    public void testFillForm(String email, String password){

        System.out.println(email);
        System.out.println(password);

    }


    @DataProvider(name ="hashMapData")  // Multi dimensional array of objects
    public Object[][] hashMapData(){

        HashMap<String,String> user1 = new HashMap<>();
        user1.put("email","user3@yahoo.com");
        user1.put("password","Pass@123");

        HashMap<String ,String> user2 = new HashMap<>();
        user2.put("email","user4@gmail.com");
        user2.put("password","Pass@12345");


        return new Object[][]  { {user1},{user2}  };
    }

    @Test(dataProvider = "hashMapData")
    public void testFormWithHashMapData( HashMap<String ,String>data){

        System.out.println(data.get("email"));
        System.out.println(data.get("password"));

    }















}
