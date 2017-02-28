package com.api;

import com.model.ManageURL;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CreateCompanyAPI {

    ManageURL baseURL = new ManageURL();


   @BeforeTest
   public void setURL(){

   }



    @Test
    public void createCompany(){
        baseURL.setURL();
        String URL = baseURL.getURL();
        String mainURL = URL + "company/";
        System.out.println(mainURL);

    }

}
